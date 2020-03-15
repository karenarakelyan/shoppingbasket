package com.karen.shoppingbasket.security.exception;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(final String message) {
        super(message);
    }

    public InvalidCredentialsException(final Throwable cause) {
        super(cause);
    }
}