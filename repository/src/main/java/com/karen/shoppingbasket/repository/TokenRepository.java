package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.token.Token;
import com.karen.shoppingbasket.entity.token.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValueAndTokenType(String value, TokenType tokenType);

    Token findByValue(String value);

}
