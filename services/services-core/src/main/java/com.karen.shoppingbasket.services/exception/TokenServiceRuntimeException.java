package com.karen.shoppingbasket.services.exception;

public class TokenServiceRuntimeException extends RuntimeException {

    public TokenServiceRuntimeException() {
    }

    public TokenServiceRuntimeException(final String message) {
        super(message);
    }

    public TokenServiceRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TokenServiceRuntimeException(final Throwable cause) {
        super(cause);
    }

    public TokenServiceRuntimeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
