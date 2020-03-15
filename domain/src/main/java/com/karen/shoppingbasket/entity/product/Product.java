package com.karen.shoppingbasket.entity.product;

import com.karen.shoppingbasket.entity.SoftDeletableBaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "UK_product_name_deletedon", columnNames = {"name", "deletedon"})
})
public class Product extends SoftDeletableBaseEntity {

    private Product() {

    }

    public Product(final String name, final String description, final String type, final BigDecimal price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.setCreatedOn(LocalDateTime.now());
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Product product = (Product) o;

        return new EqualsBuilder()
                .append(getId(), product.getId())
                .append(name, product.name)
                .append(description, product.description)
                .append(type, product.type)
                .append(price, product.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(name)
                .append(description)
                .append(type)
                .append(price)
                .toHashCode();
    }
}
