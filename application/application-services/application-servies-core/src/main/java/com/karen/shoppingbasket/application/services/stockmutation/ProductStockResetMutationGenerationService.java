package com.karen.shoppingbasket.application.services.stockmutation;

/**
 * @author Karen Arakelyan
 */

public interface ProductStockResetMutationGenerationService {

    void createResetMutation(Long productId, Integer quantity);

}
