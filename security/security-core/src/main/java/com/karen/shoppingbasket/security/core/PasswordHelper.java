package com.karen.shoppingbasket.security.core;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Karen Arakelyan
 */

@Component
public class PasswordHelper {

    private static final String SALT = BCrypt.gensalt(11);

    public String createHashedPassword(final String plainPassword) {
        Assert.hasText(plainPassword, "plainPassword cannot be null or empty.");
        return BCrypt.hashpw(plainPassword, SALT);
    }

    public boolean isPasswordCorrect(final String plainPassword, final String hashedPassword) {
        Assert.hasText(plainPassword, "plainPassword cannot be null or empty.");
        Assert.hasText(hashedPassword, "hashedPassword cannot be null or empty.");
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
