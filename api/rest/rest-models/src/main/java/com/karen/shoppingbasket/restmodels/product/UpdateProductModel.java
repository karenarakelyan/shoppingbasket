package com.karen.shoppingbasket.restmodels.product;

/**
 * @author Karen Arakelyan
 */

public class UpdateProductModel extends ProductModel {

    private Integer stockQuantity;

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(final Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
