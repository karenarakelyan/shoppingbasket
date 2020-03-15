package com.karen.shoppingbasket.entity.token;

import com.karen.shoppingbasket.SoftDeletableBaseEntity;
import com.karen.shoppingbasket.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
public class Token extends SoftDeletableBaseEntity {

    @Column(name = "value", nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TokenType tokenType;

    @Column(name = "expirationdate", nullable = false)
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "userid", foreignKey = @ForeignKey(name = "FK_token_user"))
    private User owner;

    public Token() {
        super();
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(final TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }
}