package com.karen.shoppingbasket.restmodels.order;

import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;

/**
 * @author Karen Arakelyan
 */

public class OrderProductResponseModel {

    private final ProductInformationResponseModel productInformationResponseModel;

    private final Integer quantity;

    public OrderProductResponseModel(final ProductInformationResponseModel productInformationResponseModel, final Integer quantity) {
        this.productInformationResponseModel = productInformationResponseModel;
        this.quantity = quantity;
    }

    public ProductInformationResponseModel getProductInformationResponseModel() {
        return productInformationResponseModel;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
