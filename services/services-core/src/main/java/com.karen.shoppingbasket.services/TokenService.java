package com.karen.shoppingbasket.services;

import com.karen.shoppingbasket.entity.token.Token;
import com.karen.shoppingbasket.entity.token.TokenType;

public interface TokenService {

    Token getToken(String value, TokenType tokenType);

    Token createToken(Long userId, TokenType tokenType);

    Token removeToken(String token);
}
