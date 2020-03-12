package com.karen.shoppingbasket.services;

import com.karen.shoppingbasket.entity.stock.MutationType;

/**
 * @author Karen Arakelyan
 */

public interface StockMutationService {

    Long createStockMutation(Long productId, MutationType type, Integer count);

    Long calculateStock(Long productId);

}
