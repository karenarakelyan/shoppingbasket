package com.karen.shoppingbasket.restmodels.authentication;


import com.karen.shoppingbasket.restmodels.user.UserResponseModel;

import java.time.LocalDateTime;

public class AuthenticationResponseModel {

    private final UserResponseModel user;

    private final String token;

    private final LocalDateTime expirationDate;

    public AuthenticationResponseModel(
            final UserResponseModel user,
            final String token,
            final LocalDateTime expirationDate
    ) {
        this.user = user;
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public UserResponseModel getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
}