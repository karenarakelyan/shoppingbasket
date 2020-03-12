package com.karen.shoppingbasket.entity.order;

import com.karen.shoppingbasket.entity.ActionTracesAwareBaseEntity;
import com.karen.shoppingbasket.entity.product.Product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Karen Arakelyan
 */
@Entity
@Table(name = "order_product")
public class OrderProduct extends ActionTracesAwareBaseEntity {

    private OrderProduct() {
    }

    public OrderProduct(final Product product) {
        this.product = product;
    }

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }
}
