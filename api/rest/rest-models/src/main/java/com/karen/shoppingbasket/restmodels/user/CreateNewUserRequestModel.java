package com.karen.shoppingbasket.restmodels.user;

import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */

public class CreateNewUserRequestModel {

    private String email;

    private String password;

    private String name;

    private String surname;

    private LocalDateTime birthday;

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

}
