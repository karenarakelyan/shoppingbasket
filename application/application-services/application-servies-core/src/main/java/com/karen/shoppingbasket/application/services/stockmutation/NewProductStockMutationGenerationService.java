package com.karen.shoppingbasket.application.services.stockmutation;

/**
 * @author Karen Arakelyan
 */

public interface NewProductStockMutationGenerationService {

    void createResetMutation(Long productId, Integer quantity);

}
