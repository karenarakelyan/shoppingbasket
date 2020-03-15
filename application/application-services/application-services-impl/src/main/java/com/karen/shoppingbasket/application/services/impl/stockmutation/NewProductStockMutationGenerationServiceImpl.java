package com.karen.shoppingbasket.application.services.impl.stockmutation;

import com.karen.shoppingbasket.application.services.stockmutation.NewProductStockMutationGenerationService;
import com.karen.shoppingbasket.entity.stock.MutationType;
import com.karen.shoppingbasket.services.StockMutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Karen Arakelyan
 */

@Service
public class NewProductStockMutationGenerationServiceImpl implements NewProductStockMutationGenerationService {

    private final StockMutationService stockMutationService;

    @Autowired
    public NewProductStockMutationGenerationServiceImpl(final StockMutationService stockMutationService) {
        this.stockMutationService = stockMutationService;
    }

    @Override
    public void createResetMutation(final Long productId, final Integer quantity) {
        stockMutationService.createStockMutation(productId, MutationType.RESET, quantity);
    }
}
