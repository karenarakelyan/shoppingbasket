package com.karen.shoppingbasket.restmodels.product;

/**
 * @author Karen Arakelyan
 */

public class ProductInformationResponseModel extends ProductModel {

    private Long id;

    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }
}
