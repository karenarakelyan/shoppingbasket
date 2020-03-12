package com.karen.shoppingbasket.entity.stock;

import com.karen.shoppingbasket.entity.ActionTracesAwareBaseEntity;
import com.karen.shoppingbasket.entity.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Karen Arakelyan
 */
@Entity
@Table(name = "stock_mutation")
public class StockMutation extends ActionTracesAwareBaseEntity {

    public StockMutation() {
    }

    public StockMutation(final Product product, final MutationType mutationType, final Integer mutationCount) {
        this.product = product;
        this.mutationType = mutationType;
        this.mutationCount = mutationCount;
    }

    @ManyToOne
    private Product product;

    @Column(name = "mutation_type", nullable = false)
    private MutationType mutationType;

    @Column(name = "mutation_count", nullable = false)
    private Integer mutationCount;

    public Product getProduct() {
        return product;
    }

    public MutationType getMutationType() {
        return mutationType;
    }

    public Integer getMutationCount() {
        return mutationCount;
    }

}
