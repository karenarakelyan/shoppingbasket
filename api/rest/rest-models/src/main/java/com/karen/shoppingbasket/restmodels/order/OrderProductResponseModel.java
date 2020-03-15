package com.karen.shoppingbasket.restmodels.order;

import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;

/**
 * @author Karen Arakelyan
 */

public class OrderProductResponseModel {

    private final ProductInformationResponseModel product;

    private final Integer orderedQuantity;

    public OrderProductResponseModel(final ProductInformationResponseModel product, final Integer orderedQuantity) {
        this.product = product;
        this.orderedQuantity = orderedQuantity;
    }

    public ProductInformationResponseModel getProduct() {
        return product;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }
}
