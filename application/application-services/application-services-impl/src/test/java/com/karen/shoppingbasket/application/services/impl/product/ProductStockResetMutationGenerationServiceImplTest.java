package com.karen.shoppingbasket.application.services.impl.product;

import com.karen.shoppingbasket.application.services.impl.stockmutation.ProductStockResetMutationGenerationServiceImpl;
import com.karen.shoppingbasket.entity.stock.MutationType;
import com.karen.shoppingbasket.services.StockMutationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Karen Arakelyan
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductStockResetMutationGenerationServiceImplTest {

    @InjectMocks
    private ProductStockResetMutationGenerationServiceImpl newProductStockMutationGenerationService;

    @Mock
    private StockMutationService stockMutationService;

    @Test
    public void testThatResetStockMutationsAreBeingGenerated() {
        newProductStockMutationGenerationService.createResetMutation(11L, 11);
        Mockito.verify(stockMutationService).createStockMutation(11L, MutationType.RESET, 11);
        Mockito.verifyNoMoreInteractions(stockMutationService);
    }


}
