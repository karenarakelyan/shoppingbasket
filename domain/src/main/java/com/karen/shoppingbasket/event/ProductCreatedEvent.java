package com.karen.shoppingbasket.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Karen Arakelyan
 */

public class ProductCreatedEvent extends ApplicationEvent {

    private final Long productId;

    private final int quantity;

    public ProductCreatedEvent(final Object source, final Long productId, final int quantity) {
        super(source);
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
