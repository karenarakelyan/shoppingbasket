package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.order.OrderDto;
import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.OrderProduct;
import com.karen.shoppingbasket.entity.order.Status;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.event.OrderCreatedEvent;
import com.karen.shoppingbasket.exception.InsufficientStockException;
import com.karen.shoppingbasket.repository.OrderRepository;
import com.karen.shoppingbasket.services.OrderService;
import com.karen.shoppingbasket.services.ProductService;
import com.karen.shoppingbasket.services.StockMutationService;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Karen Arakelyan
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final UserService userService;

    private final StockMutationService stockMutationService;

    private final ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,
                            final ProductService productService,
                            final UserService userService,
                            final StockMutationService stockMutationService,
                            final ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
        this.stockMutationService = stockMutationService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public Long createOrder(final OrderDto orderDto, final Long userId) {
        Assert.notNull(orderDto, "Order Dto must not be null");
        Assert.notEmpty(orderDto.getProductsWithQuantities(), "Product ids must not be null");
        Assert.notNull(userId, "User id must not be null");
        final Set<OrderProduct> orderProducts = createOrderProducts(orderDto.getProductsWithQuantities());
        checkStockQuantities(orderProducts);
        final User user = userService.findById(userId);
        final Order order = new Order(Status.ORDERED, orderProducts, user);
        final Order savedOrder = orderRepository.save(order);
        final Map<Long, Integer> productAndQuantitiesMap = savedOrder.getOrderProducts()
                .stream()
                .collect(Collectors.toMap(e -> e.getProduct().getId(), OrderProduct::getQuantity));
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(this, productAndQuantitiesMap));
        return savedOrder.getId();
    }

    @Override
    @Transactional
    public Order changeOrderStatus(final Long id, final Status status) {
        Assert.notNull(id, "Order id must not be null");
        Assert.notNull(status, "Order status must not be null");
        final Order order = orderRepository.getOne(id);
        order.setUpdatedOn(LocalDateTime.now());
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getCustomerOrders(final Long customerId) {
        Assert.notNull(customerId, "Customer id must not be null");
        return orderRepository.findAllByUserId(customerId);
    }


    @Override
    public Order getSingleOrder(final Long orderId) {
        Assert.notNull(orderId, "Order id must not be null");
        return orderRepository.getOne(orderId);
    }

    private Set<OrderProduct> createOrderProducts(final Map<Long, Integer> productWithQuantities) {
        return productWithQuantities
                .entrySet()
                .stream()
                .map(e -> new OrderProduct(productService.findOne(e.getKey()), e.getValue()))
                .collect(Collectors.toSet());
    }

    private void checkStockQuantities(final Set<OrderProduct> orderProducts) {
        for (final OrderProduct orderProduct : orderProducts) {
            final int stock = stockMutationService.calculateStock(orderProduct.getProduct().getId());
            if (stock < orderProduct.getQuantity()) {
                throw new InsufficientStockException(orderProduct.getProduct().getId(), orderProduct.getQuantity(), stock);
            }
        }
    }


}
