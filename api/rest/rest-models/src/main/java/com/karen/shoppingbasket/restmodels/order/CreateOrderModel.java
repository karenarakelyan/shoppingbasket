package com.karen.shoppingbasket.restmodels.order;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public class CreateOrderModel {

    private List<CreateOrderProductModel> orderProductModels;

    public List<CreateOrderProductModel> getOrderProductModels() {
        return orderProductModels;
    }

    public void setOrderProductModels(final List<CreateOrderProductModel> orderProductModels) {
        this.orderProductModels = orderProductModels;
    }
}
