package com.karen.shoppingbasket.dto.product;

import java.math.BigDecimal;

/**
 * @author Karen Arakelyan
 */

public class ProductDto {

    private String name;

    private String description;

    private String type;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
