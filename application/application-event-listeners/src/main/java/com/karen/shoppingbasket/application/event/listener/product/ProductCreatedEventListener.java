package com.karen.shoppingbasket.application.event.listener.product;

import com.karen.shoppingbasket.application.services.stockmutation.NewProductStockMutationGenerationService;
import com.karen.shoppingbasket.event.ProductCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class ProductCreatedEventListener implements ApplicationListener<ProductCreatedEvent> {

    private final NewProductStockMutationGenerationService stockMutationGenerationService;

    @Autowired
    public ProductCreatedEventListener(final NewProductStockMutationGenerationService stockMutationGenerationService) {
        this.stockMutationGenerationService = stockMutationGenerationService;
    }

    @Override
    public void onApplicationEvent(final ProductCreatedEvent productCreatedEvent) {
        stockMutationGenerationService.createResetMutation(productCreatedEvent.getProductId(), productCreatedEvent.getQuantity());
    }
}
