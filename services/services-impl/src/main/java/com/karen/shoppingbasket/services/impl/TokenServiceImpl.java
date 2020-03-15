package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.entity.token.Token;
import com.karen.shoppingbasket.entity.token.TokenType;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.repository.TokenRepository;
import com.karen.shoppingbasket.repository.UserRepository;
import com.karen.shoppingbasket.services.TokenService;
import com.karen.shoppingbasket.services.exception.InvalidTokenRuntimeException;
import com.karen.shoppingbasket.services.exception.TokenNotFoundRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Karen Arakelyan
 */

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    @Autowired
    public TokenServiceImpl(final TokenRepository tokenRepository, final UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Token getToken(final String tokenValue, final TokenType tokenType) {
        Assert.notNull(tokenValue, "Token must nor be null");
        Assert.notNull(tokenType, "Token type must nor be null");
        final Token token = tokenRepository.findByValueAndTokenType(tokenValue, tokenType);
        if (token == null) {
            throw new TokenNotFoundRuntimeException(tokenValue);
        }
        if (token.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenRuntimeException(tokenValue);
        }
        if (token.getDeletedOn() != null)
            throw new InvalidTokenRuntimeException(tokenValue);
        return token;
    }

    @Override
    public Token createToken(final Long userId, final TokenType tokenType) {
        Assert.notNull(userId, "User id must not be null");
        final List<Token> tokens = tokenRepository.findAll();
        for (final Token token : tokens) {
            token.setDeletedOn(LocalDateTime.now());
        }
        final User user = userRepository.getOne(userId);
        final Token token = new Token();
        token.setOwner(user);
        token.setExpirationDate(LocalDateTime.now().plus(tokenType.getExpiresIn()));
        token.setValue(UUID.randomUUID().toString());
        token.setTokenType(tokenType);
        token.setCreatedOn(LocalDateTime.now());
        tokens.add(token);
        tokenRepository.saveAll(tokens);
        return token;
    }

    @Override
    public Token removeToken(final String tokenValue) {
        Assert.notNull(tokenValue, "Token must not be null");
        final Token token = tokenRepository.findByValue(tokenValue);
        if (token == null) {
            throw new EntityNotFoundException(String.format("Not found token by value '%s'", tokenValue));
        }
        token.setDeletedOn(LocalDateTime.now());
        return tokenRepository.save(token);
    }

}
