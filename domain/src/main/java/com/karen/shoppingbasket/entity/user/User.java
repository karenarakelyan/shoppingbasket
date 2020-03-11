package com.karen.shoppingbasket.entity.user;

import com.karen.shoppingbasket.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author Karen Arakelyan
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwword;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPasswword() {
        return passwword;
    }

    public void setPasswword(final String passwword) {
        this.passwword = passwword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }
}
