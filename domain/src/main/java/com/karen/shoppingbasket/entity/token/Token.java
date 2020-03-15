package com.karen.shoppingbasket.entity.token;

import com.karen.shoppingbasket.entity.SoftDeletableBaseEntity;
import com.karen.shoppingbasket.entity.user.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Token token = (Token) o;

        return new EqualsBuilder()
                .append(value, token.value)
                .append(tokenType, token.tokenType)
                .append(expirationDate, token.expirationDate)
                .append(owner, token.owner)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .append(tokenType)
                .append(expirationDate)
                .append(owner)
                .toHashCode();
    }
}