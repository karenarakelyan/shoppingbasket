package com.karen.shoppingbasket.exception;

/**
 * @author Karen Arakelyan
 */

public class InsufficientStockException extends RuntimeException {

    private String message;

    public InsufficientStockException(final Long productId, final int quantityToBeOrdered, final int actualStock) {
        this.message = String.format("The stock quantity for product with id '%s' is less than you wanted to order (Quantity Requested '%s', Actual Quantity '%s')", productId, quantityToBeOrdered, actualStock);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
