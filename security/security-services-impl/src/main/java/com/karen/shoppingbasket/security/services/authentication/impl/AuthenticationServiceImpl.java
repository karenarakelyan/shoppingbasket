package com.karen.shoppingbasket.security.services.authentication.impl;

import com.karen.shoppingbasket.dto.authentication.SuccessfullyAuthenticatedDto;
import com.karen.shoppingbasket.entity.token.Token;
import com.karen.shoppingbasket.entity.token.TokenType;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.security.core.PasswordHelper;
import com.karen.shoppingbasket.security.exception.InvalidCredentialsException;
import com.karen.shoppingbasket.security.services.authentication.AuthenticationService;
import com.karen.shoppingbasket.services.TokenService;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Karen Arakelyan
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenService tokenService;

    private final UserService userService;

    private final PasswordHelper passwordHelper;

    @Autowired
    public AuthenticationServiceImpl(final TokenService tokenService, final UserService userService, final PasswordHelper passwordHelper) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordHelper = passwordHelper;
    }

    @Override
    public SuccessfullyAuthenticatedDto authenticateWithCredentials(final String username, final String password) {
        Assert.hasText(username, "Email must not be empty");
        Assert.hasText(password, "Password must not be empty");
        final User user = userService.findByUsername(username);
        if (!passwordHelper.isPasswordCorrect(password, user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password.");
        }
        final Token token = tokenService.createToken(user.getId(), TokenType.AUTHENTICATION);
        return new SuccessfullyAuthenticatedDto(user, token.getValue(), token.getExpirationDate());
    }

    @Override
    public SuccessfullyAuthenticatedDto authenticateWithToken(final String tokenValue) {
        Assert.notNull(tokenValue, "Token must not be null");
        final Token token = tokenService.getToken(tokenValue, TokenType.AUTHENTICATION);
        return new SuccessfullyAuthenticatedDto(token.getOwner(), token.getValue(), token.getExpirationDate());
    }

    @Override
    public void logoutWithToken(final String tokenValue) {
        Assert.notNull(tokenValue, "Token must not be null");
        tokenService.removeToken(tokenValue);
    }


}
