package com.karen.shoppingbasket.entity.stock;

import com.karen.shoppingbasket.entity.ActionTracesAwareBaseEntity;
import com.karen.shoppingbasket.entity.product.Product;

import javax.persistence.*;

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
    @JoinColumn(name = "productid", foreignKey = @ForeignKey(name = "FK_stock_mutation_product"))
    private Product product;

    @Column(name = "mutationtype", nullable = false)
    @Enumerated(EnumType.STRING)
    private MutationType mutationType;

    @Column(name = "mutationcount", nullable = false)
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
