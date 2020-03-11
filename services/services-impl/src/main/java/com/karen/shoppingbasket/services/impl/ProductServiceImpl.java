package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.repository.ProductRepository;
import com.karen.shoppingbasket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findOne(final Long id) {
        Assert.notNull(id, "Id must not be null");
        return productRepository.getOne(id);
    }

    @Override
    public Long create(final ProductDto productDto) {
        assertDto(productDto);
        final Product product = new Product();
        setProductProperties(productDto, product);
        product.setCreatedOn(LocalDateTime.now());
        final Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    @Override
    public Product update(final Long id, final ProductDto productDto) {
        Assert.notNull(id, "Id must not be null");
        assertDto(productDto);
        final Product product = productRepository.getOne(id);
        setProductProperties(productDto, product);
        product.setUpdatedOn(LocalDateTime.now());
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(final Long id) {
        Assert.notNull(id, "Id must not be null");
        productRepository.deleteById(id);
    }

    private void setProductProperties(final ProductDto productDto, final Product product) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setType(productDto.getType());
        product.setPrice(productDto.getPrice());
    }

    private void assertDto(final ProductDto productDto) {
        Assert.notNull(productDto, "Product Dto must not be null");
        Assert.notNull(productDto.getName(), "Product name must not be null");
        Assert.notNull(productDto.getPrice(), "Product price must not be null");
        Assert.notNull(productDto.getType(), "Product type must not be null");
    }

}
