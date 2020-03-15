package com.karen.shoppingbasket.facade.order;

import com.karen.shoppingbasket.restmodels.order.CreateOrderModel;
import com.karen.shoppingbasket.restmodels.order.OrderResponseModel;
import com.karen.shoppingbasket.restmodels.order.OrderStatus;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public interface OrderFacade {

    Long createOrder(CreateOrderModel createOrderModel, Long userId);

    List<OrderResponseModel> getAllOrders(final Long userId);

    OrderResponseModel getOrder(Long orderId, final Long userId);

    void changeOrderStatus(Long orderId, OrderStatus orderStatus);

}
