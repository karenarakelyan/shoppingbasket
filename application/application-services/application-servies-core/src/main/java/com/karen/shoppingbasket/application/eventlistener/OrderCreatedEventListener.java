package com.karen.shoppingbasket.application.eventlistener;

import com.karen.shoppingbasket.application.services.stockmutation.ProductStockDeductionService;
import com.karen.shoppingbasket.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author Karen Arakelyan
 */

@Service
public class OrderCreatedEventListener implements ApplicationListener<OrderCreatedEvent> {

    private ProductStockDeductionService productStockDeductionService;

    @Autowired
    public OrderCreatedEventListener(final ProductStockDeductionService productStockDeductionService) {
        this.productStockDeductionService = productStockDeductionService;
    }

    @Override
    public void onApplicationEvent(final OrderCreatedEvent orderCreatedEvent) {
        productStockDeductionService.deductStock(orderCreatedEvent.getProductQuantities());
    }

}
