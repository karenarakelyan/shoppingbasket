package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.order.OrderDto;
import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.OrderProduct;
import com.karen.shoppingbasket.entity.order.Status;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.repository.OrderRepository;
import com.karen.shoppingbasket.services.OrderService;
import com.karen.shoppingbasket.services.ProductService;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,
                            final ProductService productService,
                            final UserService userService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public Long createOrder(final OrderDto orderDto, final Long userId) {
        Assert.notNull(orderDto, "Order Dto must not be null");
        Assert.notEmpty(orderDto.getProductsWithQuantities(), "Product ids must not be null");
        Assert.notNull(userId, "User id must not be null");
        final Set<OrderProduct> orderProducts = createOrderProducts(orderDto.getProductsWithQuantities());
        final User user = userService.findById(userId);
        final Order order = new Order(Status.ORDERED, orderProducts, user);
        final Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Override
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
        return orderRepository.getByUserId(customerId);
    }


    @Override
    public Order getSingleOrder(Long orderId) {
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


}
