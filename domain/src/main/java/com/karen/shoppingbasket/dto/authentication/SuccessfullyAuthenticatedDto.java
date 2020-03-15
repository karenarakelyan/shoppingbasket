package com.karen.shoppingbasket.dto.authentication;

import com.karen.shoppingbasket.entity.user.User;

import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */

public class SuccessfullyAuthenticatedDto {

    final User user;

    final String token;

    final LocalDateTime expirationDate;

    public SuccessfullyAuthenticatedDto(final User user, final String token, final LocalDateTime expirationDate) {
        this.user = user;
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

}
