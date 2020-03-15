package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.token.Token;
import com.karen.shoppingbasket.entity.token.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    /**
     * Find Token with given value and type
     *
     * @param value     value of token
     * @param tokenType type of token
     * @return Token with given value and type
     */
    Token findByValueAndTokenType(String value, TokenType tokenType);

    /**
     * Find Token with given value
     *
     * @param value value of token
     * @return Token with given value
     */
    Token findByValue(String value);

}
