package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.entity.stock.MutationType;
import com.karen.shoppingbasket.entity.stock.StockMutation;
import com.karen.shoppingbasket.repository.StockMutationRepository;
import com.karen.shoppingbasket.services.ProductService;
import com.karen.shoppingbasket.services.StockMutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Karen Arakelyan
 */

@Service
public class StockMutationServiceImpl implements StockMutationService {

    private final StockMutationRepository stockMutationRepository;

    private final ProductService productService;

    @Autowired
    public StockMutationServiceImpl(final StockMutationRepository stockMutationRepository,
                                    final ProductService productService) {
        this.stockMutationRepository = stockMutationRepository;
        this.productService = productService;
    }

    @Override
    public Long createStockMutation(final Long productId, final MutationType type, final Integer count) {
        Assert.notNull(productId, "Product id must not be null");
        Assert.notNull(type, "Mutation type must not be null");
        Assert.notNull(count, "Mutation Count must not be null");
        final Product product = fetchAndAssertProduct(productId);
        final StockMutation stockMutation = new StockMutation(product, type, parseMutation(type, count));
        return stockMutationRepository.save(stockMutation).getId();
    }

    @Override
    public int calculateStock(final Long productId) {
        Assert.notNull(productId, "Product id must not be null");
        fetchAndAssertProduct(productId);
        final StockMutation lastResetMutationForProduct = stockMutationRepository.getLastResetMutationForProduct(productId);
        Assert.notNull(lastResetMutationForProduct, "Stock for this product is corrupted");
        return stockMutationRepository.calculateSumByProductId(productId, lastResetMutationForProduct.getId());
    }

    private Product fetchAndAssertProduct(final Long productId) {
        final Product product = productService.findOne(productId);
        Assert.notNull(product, String.format("Not found product with id '%s'", productId));
        return product;
    }

    private int parseMutation(final MutationType type, final Integer count) {
        if (MutationType.IN.equals(type)) {
            return Math.abs(count);
        } else {
            return Math.abs(count) * (-1);
        }
    }

}
