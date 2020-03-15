package com.karen.shoppingbasket.restmodels.authentication;

import javax.validation.constraints.NotBlank;

public class TokenAuthenticationRequestModel {

    @NotBlank
    private String token;

    public TokenAuthenticationRequestModel() {
        super();
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}