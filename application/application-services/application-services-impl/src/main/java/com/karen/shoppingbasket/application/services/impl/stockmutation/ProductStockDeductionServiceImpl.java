package com.karen.shoppingbasket.application.services.impl.stockmutation;

import com.karen.shoppingbasket.application.services.stockmutation.ProductStockDeductionService;
import com.karen.shoppingbasket.entity.stock.MutationType;
import com.karen.shoppingbasket.services.StockMutationService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Karen Arakelyan
 */

@Service
public class ProductStockDeductionServiceImpl implements ProductStockDeductionService {

    private final StockMutationService stockMutationService;

    public ProductStockDeductionServiceImpl(final StockMutationService stockMutationService) {
        this.stockMutationService = stockMutationService;
    }

    @Override
    public void deductStock(final Map<Long, Integer> productQuantities) {
        for (final Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            stockMutationService.createStockMutation(entry.getKey(), MutationType.OUT, entry.getValue());
        }
    }

}
