package com.karen.shoppingbasket.restmodels.product;

/**
 * @author Karen Arakelyan
 */

public class ProductInformationResponseModel extends ProductModel {

    private Long id;

    private Integer stockQuantity;;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(final Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
