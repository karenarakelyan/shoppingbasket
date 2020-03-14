package com.karen.shoppingbasket.restmodels.order;

/**
 * @author Karen Arakelyan
 */

public class CreateOrderProductModel {

    private Long productId;

    private Long quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }
}
