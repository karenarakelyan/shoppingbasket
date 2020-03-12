package com.karen.shoppingbasket.services;

import com.karen.shoppingbasket.entity.order.Order;
import com.karen.shoppingbasket.entity.order.Status;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public interface OrderService {

    Long createOrder(List<Long> productIds, Long userId);

    Order changeOrderStatus(Long id, Status status);

    List<Order> getAllOrders();

    List<Order> getCustomerOrders(final Long customerId);

}
