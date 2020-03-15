package com.karen.shoppingbasket.security.core;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Karen Arakelyan
 */

public enum UserRole implements GrantedAuthority {

    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String role;

    UserRole(final String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + getRole();
    }

}
