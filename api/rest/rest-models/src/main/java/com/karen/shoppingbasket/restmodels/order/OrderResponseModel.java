package com.karen.shoppingbasket.restmodels.order;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public class OrderResponseModel {

    private final List<OrderProductResponseModel> products;

    private final OrderStatus orderStatus;

    public OrderResponseModel(final List<OrderProductResponseModel> products, final OrderStatus orderStatus) {
        this.products = products;
        this.orderStatus = orderStatus;
    }

    public List<OrderProductResponseModel> getProducts() {
        return products;
    }
}
