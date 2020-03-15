package com.karen.shoppingbasket.restmodels.authentication;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthenticationRequestModel {

    @Email
    @JsonProperty(value = "email")
    private String email;

    @NotBlank
    @JsonProperty(value = "password")
    private String password;

    public AuthenticationRequestModel() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}