package com.karen.shoppingbasket.services.exception;

public class TokenNotFoundRuntimeException extends TokenServiceRuntimeException {

    private final String value;

    public TokenNotFoundRuntimeException(final String value) {
        super("Token not found.");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
