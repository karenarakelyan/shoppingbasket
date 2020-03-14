package com.karen.shoppingbasket.restmodels.order;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public class OrderResponseModel {

    private final List<OrderProductResponseModel> orderProductResponseModels;

    private final OrderStatus orderStatus;

    public OrderResponseModel(final List<OrderProductResponseModel> orderProductResponseModels, final OrderStatus orderStatus) {
        this.orderProductResponseModels = orderProductResponseModels;
        this.orderStatus = orderStatus;
    }

    public List<OrderProductResponseModel> getOrderProductResponseModels() {
        return orderProductResponseModels;
    }
}
