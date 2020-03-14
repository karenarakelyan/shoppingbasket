package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.entity.stock.MutationType;
import com.karen.shoppingbasket.entity.stock.StockMutation;
import com.karen.shoppingbasket.repository.StockMutationRepository;
import com.karen.shoppingbasket.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * @author Karen Arakelyan
 */

@RunWith(MockitoJUnitRunner.class)
public class StockMutationServiceImplTest {

    @InjectMocks
    private StockMutationServiceImpl stockMutationService;

    @Mock
    private ProductService productService;

    @Mock
    private StockMutationRepository stockMutationRepository;

    @Captor
    private ArgumentCaptor<StockMutation> stockMutationArgumentCaptor;

    @Test
    public void testThatStockMutationBeingCreatedSuccessfully() {
        final Long productId = 123L;
        final Product product = new Product("name", null, null, null);
        product.setId(productId);
        when(productService.findOne(productId)).thenReturn(product);
        when(stockMutationRepository.save(isA(StockMutation.class))).thenAnswer((Answer<StockMutation>) invocationOnMock -> {
            final StockMutation argument = invocationOnMock.getArgument(0);
            argument.setId(1L);
            return argument;
        });
        final Long result = stockMutationService.createStockMutation(productId, MutationType.OUT, 10);
        verify(productService).findOne(productId);
        verify(stockMutationRepository).save(stockMutationArgumentCaptor.capture());
        assertEquals(Long.valueOf(1L), result);
        final StockMutation capturedStockMutation = stockMutationArgumentCaptor.getValue();
        assertEquals(Integer.valueOf(-10), capturedStockMutation.getMutationCount());
        assertEquals(MutationType.OUT, capturedStockMutation.getMutationType());
        assertEquals(productId, capturedStockMutation.getProduct().getId());
        verifyNoMoreInteractions(stockMutationRepository, productService);
    }

    @Test
    public void shouldThrowAnExceptionWhenWrongArgumentsPassedToCreateStockMutation() {
        try {
            stockMutationService.createStockMutation(null, MutationType.OUT, 10);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            stockMutationService.createStockMutation(123L, null, 10);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            stockMutationService.createStockMutation(123L, MutationType.OUT, null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenWrongProductProvidedDuringStockMutationCreation() {
        when(productService.findOne(123L)).thenReturn(null);
        stockMutationService.createStockMutation(123L, MutationType.IN, 10);
    }

    @Test
    public void shouldCalculateTotalStockOfGivenProduct() {
        final Long productId = 123L;
        final Product product = new Product("name", null, null, null);
        final StockMutation lastResetMutation = new StockMutation();
        lastResetMutation.setId(111L);
        product.setId(productId);
        when(productService.findOne(productId)).thenReturn(product);
        when(stockMutationRepository.getLastResetMutationForProduct(productId)).thenReturn(lastResetMutation);
        when(stockMutationRepository.calculateSumByProductId(productId, lastResetMutation.getId())).thenReturn(111);
        final int stock = stockMutationService.calculateStock(productId);
        verify(productService).findOne(productId);
        verify(stockMutationRepository).getLastResetMutationForProduct(productId);
        verify(stockMutationRepository).calculateSumByProductId(productId, lastResetMutation.getId());
        verifyNoMoreInteractions(productService, stockMutationRepository);
        assertEquals(111L, stock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenWrongProductProvidedDuringStockMutationCalculation() {
        when(productService.findOne(123L)).thenReturn(null);
        stockMutationService.calculateStock(123L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenNoResetMutationDuringStockMutationCalculation() {
        final long productId = 123L;
        final Product product = new Product("name", null, null, null);
        when(productService.findOne(productId)).thenReturn(product);
        when(stockMutationRepository.getLastResetMutationForProduct(productId)).thenReturn(null);
        stockMutationService.calculateStock(productId);
    }

}
