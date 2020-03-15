package com.karen.shoppingbasket.application.eventlistener;

import com.karen.shoppingbasket.application.services.stockmutation.ProductStockResetMutationGenerationService;
import com.karen.shoppingbasket.event.ProductCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class ProductCreatedEventListener implements ApplicationListener<ProductCreatedEvent> {

    private final ProductStockResetMutationGenerationService resetStockMutationGenerationService;

    @Autowired
    public ProductCreatedEventListener(final ProductStockResetMutationGenerationService resetStockMutationGenerationService) {
        this.resetStockMutationGenerationService = resetStockMutationGenerationService;
    }

    @Override
    public void onApplicationEvent(final ProductCreatedEvent productCreatedEvent) {
        resetStockMutationGenerationService.createResetMutation(productCreatedEvent.getProductId(), productCreatedEvent.getQuantity());
    }
}
