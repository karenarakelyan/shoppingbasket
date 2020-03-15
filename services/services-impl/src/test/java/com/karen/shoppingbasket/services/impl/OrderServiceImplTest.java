package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.order.OrderDto;
import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.Status;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.event.OrderCreatedEvent;
import com.karen.shoppingbasket.exception.InsufficientStockException;
import com.karen.shoppingbasket.repository.OrderRepository;
import com.karen.shoppingbasket.services.ProductService;
import com.karen.shoppingbasket.services.StockMutationService;
import com.karen.shoppingbasket.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author Karen Arakelyan
 */

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @Mock
    private StockMutationService stockMutationService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Test
    public void testThatOrderIsBeingCreatedSuccessfully() {
        final OrderDto orderDto = createOrderDto();
        final Long userId = 123L;
        final User user = new User();
        user.setId(userId);
        when(productService.findOne(isA(Long.class))).thenAnswer((Answer<Product>) invocationOnMock -> {
            final Product product = new Product("name", null, null, null);
            product.setId(invocationOnMock.getArgument(0));
            return product;
        });
        when(stockMutationService.calculateStock(isA(Long.class))).thenReturn(10);
        when(userService.findById(userId)).thenReturn(user);
        when(orderRepository.save(isA(Order.class))).thenAnswer(invocationOnMock -> {
            final Order order = invocationOnMock.getArgument(0);
            order.setId(123L);
            return order;
        });
        final Long reusult = orderService.createOrder(orderDto, userId);
        verify(productService).findOne(11L);
        verify(productService).findOne(22L);
        verify(productService).findOne(33L);
        verify(stockMutationService, times(3)).calculateStock(isA(Long.class));
        verify(userService).findById(userId);
        verify(orderRepository).save(orderArgumentCaptor.capture());
        verify(applicationEventPublisher).publishEvent(isA(OrderCreatedEvent.class));
        verifyNoMoreInteractions(productService, userService, orderRepository, stockMutationService, applicationEventPublisher);
        assertEquals(Long.valueOf(123L), reusult);
        final Order capturedOrder = orderArgumentCaptor.getValue();
        assertEquals(Status.ORDERED, capturedOrder.getStatus());
        assertEquals(3, capturedOrder.getOrderProducts().size());
        assertEquals(user, capturedOrder.getUser());
    }

    @Test(expected = InsufficientStockException.class)
    public void testThatExceptionIsThrownOnInsufficientStock() {
        final OrderDto orderDto = createOrderDto();
        final Long userId = 123L;
        final User user = new User();
        user.setId(userId);
        when(productService.findOne(isA(Long.class))).thenAnswer((Answer<Product>) invocationOnMock -> {
            final Product product = new Product("name", null, null, null);
            product.setId(invocationOnMock.getArgument(0));
            return product;
        });
        when(stockMutationService.calculateStock(isA(Long.class))).thenReturn(4);
        orderService.createOrder(orderDto, userId);
        verify(productService).findOne(11L);
        verify(productService).findOne(22L);
        verify(productService).findOne(33L);
        verify(stockMutationService, times(3)).calculateStock(isA(Long.class));
    }


    @Test(expected = EntityNotFoundException.class)
    public void testThatOrderCreationFailsBecauseOfWrongProductIdIsSpecified() {
        final OrderDto orderDto = createOrderDto();
        final Long userId = 123L;
        when(productService.findOne(isA(Long.class))).thenThrow(new EntityNotFoundException());
        orderService.createOrder(orderDto, userId);
    }

    @Test
    public void testThatOrderCreationFailsBecauseOfInvalidParametersProvided() {
        try {
            final Long userId = 123L;
            orderService.createOrder(null, userId);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final OrderDto orderDto = createOrderDto();
            orderService.createOrder(orderDto, null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final OrderDto orderDto = createOrderDto();
            orderService.createOrder(orderDto, null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
    }

    @Test
    public void testThatChangingOrderStatusFailsBecauseOfInvalidParameters() {
        final Long id = 123L;
        final Status status = Status.CANCELLED;
        try {
            orderService.changeOrderStatus(null, status);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            orderService.changeOrderStatus(id, null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
    }

    @Test
    public void testThatOrderStatusChangesSuccessfully() {
        final Long id = 123L;
        final Status status = Status.CANCELLED;
        final Order order = new Order(Status.ORDERED, Collections.EMPTY_SET, new User());
        when(orderRepository.getOne(id)).thenReturn(order);
        when(orderRepository.save(isA(Order.class))).thenReturn(order);
        final Order result = orderService.changeOrderStatus(id, status);
        assertEquals(status, result.getStatus());
        assertNotNull(result.getUpdatedOn());
        verify(orderRepository).getOne(id);
        verify(orderRepository).save(isA(Order.class));
        verifyNoMoreInteractions(orderRepository);
        verifyNoInteractions(productService, userService);
    }

    @Test
    public void shouldReturnAllOrders() {
        when(orderRepository.findAll()).thenReturn(generateOrders());
        final List<Order> allOrders = orderService.getAllOrders();
        assertEquals(3, allOrders.size());
        verify(orderRepository).findAll();
        verifyNoMoreInteractions(orderRepository);
        verifyNoInteractions(productService);
    }

    @Test
    public void shouldReturnCustomerAllOrders() {
        final Long customerId = 123L;
        when(orderRepository.findAllByUserId(customerId)).thenReturn(generateOrders());
        final List<Order> allOrders = orderService.getCustomerOrders(customerId);
        assertEquals(3, allOrders.size());
        verify(orderRepository).findAllByUserId(customerId);
        verifyNoMoreInteractions(orderRepository);
        verifyNoInteractions(productService);
    }

    @Test
    public void shouldReturnSingleOrder() {
        final Long orderId = 123L;
        final Order order = new Order(Status.ORDERED, Collections.EMPTY_SET, new User());
        order.setId(orderId);
        when(orderRepository.getOne(orderId)).thenReturn(order);
        final Order fetchedOrder = orderService.getSingleOrder(orderId);
        verify(orderRepository).getOne(orderId);
        verifyNoMoreInteractions(orderRepository);
        assertEquals(order, fetchedOrder);
    }


    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowExceptionWhenOrderIsNotFound() {
        final Long orderId = 123L;
        when(orderRepository.getOne(orderId)).thenThrow(new EntityNotFoundException());
        orderService.getSingleOrder(orderId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenOrderIdIsNull() {
        orderService.getSingleOrder(null);
    }

    private OrderDto createOrderDto() {
        final List<Long> productIds = Arrays.asList(11L, 22L, 33L, 22L);
        final OrderDto orderDto = new OrderDto();
        productIds.forEach(productId -> orderDto.addProduct(productId, 5L));
        return orderDto;
    }

    private List<Order> generateOrders() {
        final List<Order> orders = new ArrayList<>();
        orders.add(new Order(Status.ORDERED, Collections.EMPTY_SET, new User()));
        orders.add(new Order(Status.ORDERED, Collections.EMPTY_SET, new User()));
        orders.add(new Order(Status.ORDERED, Collections.EMPTY_SET, new User()));
        return orders;
    }

}
