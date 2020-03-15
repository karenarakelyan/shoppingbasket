package com.karen.shoppingbasket.restmodels.order;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public class MultipleOrdersResponseModel {

    private final List<OrderResponseModel> orders;

    public MultipleOrdersResponseModel(final List<OrderResponseModel> orders) {
        this.orders = orders;
    }

    public List<OrderResponseModel> getOrders() {
        return orders;
    }

}
