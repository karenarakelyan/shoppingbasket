package com.karen.shoppingbasket.dto.user;

import com.karen.shoppingbasket.entity.user.Role;

import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */

public class UserDto {

    private String username;

    private String password;

    private String name;

    private String surname;

    private LocalDateTime birthday;

    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

}
