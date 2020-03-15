package com.karen.shoppingbasket.security.core;


import com.karen.shoppingbasket.entity.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private final String token;

    private final User user;

    TokenAuthentication(final String token) {
        super(null);
        this.token = token;
        this.user = null;
        setAuthenticated(false);
    }

    TokenAuthentication(final String token, final User user) {
        super(Collections.singletonList(UserRole.valueOf(user.getRole().name())));
        this.token = token;
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    @Override
    public Object getPrincipal() {
        return getUser();
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}