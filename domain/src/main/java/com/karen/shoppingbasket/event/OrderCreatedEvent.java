package com.karen.shoppingbasket.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author Karen Arakelyan
 */

public class OrderCreatedEvent extends ApplicationEvent {

    private final Map<Long, Integer> productQuantities;

    public OrderCreatedEvent(final Object source, final Map<Long, Integer> productQuantities) {
        super(source);
        this.productQuantities = productQuantities;
    }

    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }
}
