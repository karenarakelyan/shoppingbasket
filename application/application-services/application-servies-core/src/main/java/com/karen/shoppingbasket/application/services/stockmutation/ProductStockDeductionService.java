package com.karen.shoppingbasket.application.services.stockmutation;

import java.util.Map;

/**
 * @author Karen Arakelyan
 */

public interface ProductStockDeductionService {

    void deductStock(final Map<Long, Integer> productQuantities);

}
