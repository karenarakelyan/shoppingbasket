package com.karen.shoppingbasket.restmodels.user;

/**
 * @author Karen Arakelyan
 */

public class UserResponseModel {

    private final Long id;

    private final String username;

    private final UserRole userRole;

    public UserResponseModel(final Long id, final String username, final UserRole userRole) {
        this.id = id;
        this.username = username;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
