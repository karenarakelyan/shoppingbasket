package com.karen.shoppingbasket.services;

import com.karen.shoppingbasket.dto.order.OrderDto;
import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.Status;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public interface OrderService {

    Long createOrder(OrderDto orderDto, Long userId);

    Order changeOrderStatus(Long id, Status status);

    List<Order> getAllOrders();

    List<Order> getCustomerOrders(Long customerId);

    Order getSingleOrder(Long orderId);

}
