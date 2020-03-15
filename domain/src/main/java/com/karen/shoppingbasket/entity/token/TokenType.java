package com.karen.shoppingbasket.entity.token;

import java.time.Period;
import java.time.temporal.TemporalAmount;

public enum TokenType {

    AUTHENTICATION(Period.ofDays(5));

    private final TemporalAmount expiresIn;

    TokenType(final TemporalAmount expiresIn) {
        this.expiresIn = expiresIn;
    }

    public TemporalAmount getExpiresIn() {
        return expiresIn;
    }
}
