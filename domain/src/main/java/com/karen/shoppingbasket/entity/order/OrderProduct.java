package com.karen.shoppingbasket.entity.order;

import com.karen.shoppingbasket.entity.ActionTracesAwareBaseEntity;
import com.karen.shoppingbasket.entity.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */
@Entity
@Table(name = "order_product", uniqueConstraints = {
        @UniqueConstraint(name = "UK_order_product", columnNames = {"orderid", "productid"})
})
public class OrderProduct extends ActionTracesAwareBaseEntity {

    private OrderProduct() {
    }

    public OrderProduct(final Product product, final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.setCreatedOn(LocalDateTime.now());
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid", foreignKey = @ForeignKey(name = "FK_order_product_product"))
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
