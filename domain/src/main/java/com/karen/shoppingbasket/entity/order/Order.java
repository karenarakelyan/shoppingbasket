package com.karen.shoppingbasket.entity.order;

import com.karen.shoppingbasket.entity.ActionTracesAwareBaseEntity;
import com.karen.shoppingbasket.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Karen Arakelyan
 */

@Entity
@Table(name = "order")
public class Order extends ActionTracesAwareBaseEntity {

    private Order() {

    }

    public Order(final Status status, final Set<OrderProduct> orderProducts, final User user) {
        this.status = status;
        this.orderProducts = orderProducts;
        this.user = user;
        this.setCreatedOn(LocalDateTime.now());
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private Set<OrderProduct> orderProducts;

    @ManyToOne
    private User user;

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
