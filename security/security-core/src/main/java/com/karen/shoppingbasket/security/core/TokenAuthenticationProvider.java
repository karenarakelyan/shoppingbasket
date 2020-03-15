package com.karen.shoppingbasket.security.core;


import com.karen.shoppingbasket.entity.token.Token;
import com.karen.shoppingbasket.entity.token.TokenType;
import com.karen.shoppingbasket.security.exception.InvalidCredentialsException;
import com.karen.shoppingbasket.services.TokenService;
import com.karen.shoppingbasket.services.exception.TokenServiceRuntimeException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenService tokenService;

    TokenAuthenticationProvider(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        final String tokenValue = tokenAuthentication.getToken();
        final Token token = getToken(tokenValue, TokenType.AUTHENTICATION);
        return new TokenAuthentication(token.getValue(), token.getOwner());
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return TokenAuthentication.class.isAssignableFrom(authentication);
    }

    private Token getToken(final String tokenValue, final TokenType tokenType) {
        try {
            return tokenService.getToken(tokenValue, tokenType);
        } catch (final TokenServiceRuntimeException ex) {
            throw new InvalidCredentialsException(ex);
        }
    }
}