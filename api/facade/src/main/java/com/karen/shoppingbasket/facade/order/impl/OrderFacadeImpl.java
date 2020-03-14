package com.karen.shoppingbasket.facade.order.impl;

import com.karen.shoppingbasket.dto.order.OrderDto;
import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.Status;
import com.karen.shoppingbasket.facade.order.OrderFacade;
import com.karen.shoppingbasket.restmodels.order.CreateOrderModel;
import com.karen.shoppingbasket.restmodels.order.OrderResponseModel;
import com.karen.shoppingbasket.restmodels.order.OrderStatus;
import com.karen.shoppingbasket.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Karen Arakelyan
 */

@Component
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    private final OrderMapperFacade orderMapperFacade;

    @Autowired
    public OrderFacadeImpl(final OrderService orderService, final OrderMapperFacade orderMapperFacade) {
        this.orderService = orderService;
        this.orderMapperFacade = orderMapperFacade;
    }

    @Override
    public Long createOrder(final CreateOrderModel createOrderModel, final Long userId) {
        final OrderDto orderDto = new OrderDto();
        createOrderModel
                .getOrderProductModels()
                .forEach(orderProductModel -> orderDto.addProduct(orderProductModel.getProductId(), orderProductModel.getQuantity()));
        return orderService.createOrder(orderDto, userId);
    }

    @Override
    public List<OrderResponseModel> getUserOrders(final Long userId) {
        final List<Order> customerOrders = orderService.getCustomerOrders(userId);
        return customerOrders.stream().map(orderMapperFacade::generateOrderResponseModel).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseModel> getAllOrders() {
        final List<Order> orders = orderService.getAllOrders();
        return orders.stream().map(orderMapperFacade::generateOrderResponseModel).collect(Collectors.toList());
    }

    @Override
    public OrderResponseModel getOrder(final Long orderId) {
        return orderMapperFacade.generateOrderResponseModel(orderService.getSingleOrder(orderId));
    }

    @Override
    public void changeOrderStatus(final Long orderId, final OrderStatus orderStatus) {
        orderService.changeOrderStatus(orderId, Status.valueOf(orderStatus.name()));
    }

}
