package com.karen.shoppingbasket.services.exception;

public class InvalidTokenRuntimeException extends TokenServiceRuntimeException {

    private final String value;

    public InvalidTokenRuntimeException(final String value) {
        super("token expired.");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}