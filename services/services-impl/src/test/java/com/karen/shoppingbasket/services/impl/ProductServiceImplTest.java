package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.event.ProductCreatedEvent;
import com.karen.shoppingbasket.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Karen Arakelyan
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @Test
    public void shouldReturnSingleProduct() {
        final Long id = 123L;
        final Product product = createProduct(id, "Product1", "Cool Product", BigDecimal.TEN, "Food");
        //Mock
        when(productRepository.getOne(id)).thenReturn(product);
        //Service Call
        final Product result = productService.findOne(id);
        //Verify
        verify(productRepository).getOne(id);
        verifyNoMoreInteractions(productRepository);
        //Assert
        assertEquals(product, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetProductWithNullId() {
        //Service Call
        productService.findOne(null);
    }

    @Test
    public void shouldCreateNewProductAndReturnIdOfIt() {
        final Long id = 123L;
        final ProductDto productDto = createProductDto("Product1", "Cool Product", BigDecimal.TEN, "Food");
        final Product product = createProduct(id, "Product1", "Cool Product", BigDecimal.TEN, "Food");
        when(productRepository.save(isA(Product.class))).thenReturn(product);
        //Service Call
        final Long result = productService.create(productDto);
        //Verify
        verify(productRepository).save(productArgumentCaptor.capture());
        verify(applicationEventPublisher).publishEvent(isA(ProductCreatedEvent.class));
        verifyNoMoreInteractions(productRepository, applicationEventPublisher);
        //Assert
        assertEquals(id, result);
        final Product capturedProduct = productArgumentCaptor.getValue();
        assertEquals(product.getName(), capturedProduct.getName());
        assertEquals(product.getDescription(), capturedProduct.getDescription());
        assertEquals(product.getType(), capturedProduct.getType());
        assertEquals(product.getPrice(), capturedProduct.getPrice());
        assertNotNull(capturedProduct.getCreatedOn());
    }

    @Test
    public void shouldThrowAnExceptionWhenWrongProductDtoProvidedForCreation() {
        try {
            productService.create(null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final ProductDto productDto = createProductDto(null, "Cool Product", BigDecimal.TEN, "Food");
            productService.create(productDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final ProductDto productDto = createProductDto("Prodcut1", "Cool Product", null, "Food");
            productService.create(productDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final ProductDto productDto = createProductDto("Product1", "Cool Product", BigDecimal.TEN, null);
            productService.create(productDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
    }

    @Test
    public void shouldUpdateProduct() {
        final Long id = 123L;
        final ProductDto productDto = createProductDto("Product1", "Cool Product2", BigDecimal.ONE, "Beverage");
        final Product product = createProduct(id, "Product1", "Cool Product", BigDecimal.TEN, "Food");
        //Mock
        when(productRepository.getOne(id)).thenReturn(product);
        when(productRepository.save(isA(Product.class))).thenAnswer((Answer<Product>) invocationOnMock -> invocationOnMock.getArgument(0));
        //Service Call
        productService.update(id, productDto);
        //Verify
        verify(productRepository).getOne(id);
        verify(productRepository).save(productArgumentCaptor.capture());
        verifyNoMoreInteractions(productRepository);
        //Assert
        final Product capturedProduct = productArgumentCaptor.getValue();
        assertEquals(product, capturedProduct);
        assertNotNull(capturedProduct.getUpdatedOn());

    }

    @Test
    public void shouldThrowExceptionWhenWrondProductDtoProvidedForUpdation() {
        try {
            productService.update(123L, null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final ProductDto productDto = createProductDto(null, "Cool Product", BigDecimal.TEN, "Food");
            productService.update(123L, productDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final ProductDto productDto = createProductDto("Prodcut1", "Cool Product", null, "Food");
            productService.update(123L, productDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final ProductDto productDto = createProductDto("Product1", "Cool Product", BigDecimal.TEN, null);
            productService.update(123L, productDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNotProvidedForUpdation() {
        final ProductDto productDto = createProductDto("Product1", "Cool Product2", BigDecimal.ONE, "Beverage");
        productService.update(null, productDto);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowExceptionWhenWrongIdIsProvidedForUpdation() {
        final Long id = 123L;
        final ProductDto productDto = createProductDto("Product1", "Cool Product2", BigDecimal.ONE, "Beverage");
        when(productRepository.getOne(id)).thenThrow(new EntityNotFoundException());
        productService.update(id, productDto);
    }

    @Test
    public void shouldDeleteProduct() {
        final Long id = 123L;
        final Product product = createProduct(id, "Product1", "Cool Product", BigDecimal.TEN, "Food");
        when(productRepository.getOne(id)).thenReturn(product);
        productService.delete(id);
        //Verify
        verify(productRepository).getOne(id);
        verify(productRepository).save(productArgumentCaptor.capture());
        verifyNoMoreInteractions(productRepository);
        //Assert
        final Product deletedProduct = productArgumentCaptor.getValue();
        assertNotNull(deletedProduct.getDeletedOn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenIdIsNullForDeletingProduct() {
        productService.delete(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowAnExceptionWhenProductWithProvidedIdIsNotFound() {
        final Long id = 123L;
        when(productRepository.getOne(id)).thenThrow(new EntityNotFoundException());
        productService.delete(id);
    }

    private Product createProduct(final Long id, final String name, final String description, final BigDecimal price, final String type) {
        final Product product = new Product(name, description, type, price);
        product.setId(id);
        return product;
    }

    private ProductDto createProductDto(final String name, final String description, final BigDecimal price, final String type) {
        final ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setType(type);
        productDto.setPrice(price);
        return productDto;
    }


}
