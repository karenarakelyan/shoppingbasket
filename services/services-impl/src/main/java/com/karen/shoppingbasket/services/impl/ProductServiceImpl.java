package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.event.ProductCreatedEvent;
import com.karen.shoppingbasket.repository.ProductRepository;
import com.karen.shoppingbasket.services.ProductService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Karen Arakelyan
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, final ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Product findOne(final Long id) {
        Assert.notNull(id, "Id must not be null");
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> findAll(final Boolean sortByPrice) {
        if (BooleanUtils.isTrue(sortByPrice)) {
            return productRepository.findAllByOrderByPriceDesc();
        }
        return productRepository.findAll();
    }

    @Override
    public Product findByName(final String name) {
        final Product product = productRepository.getByName(name);
        if (product == null) {
            throw new EntityNotFoundException(String.format("Not found product by name '%s", name));
        }
        return product;
    }

    @Override
    public List<Product> findByType(final String type) {
        return productRepository.findAllByType(type);
    }

    @Override
    @Transactional
    public Long create(final ProductDto productDto) {
        assertDto(productDto);
        final Product product = new Product(productDto.getName(), productDto.getDescription(), productDto.getType(), productDto.getPrice());
        final Product savedProduct = productRepository.save(product);
        createOrResetStockForProduct(savedProduct.getId(), productDto.getStockQuantity() == null ? 0 : productDto.getStockQuantity());
        return savedProduct.getId();
    }

    @Override
    @Transactional
    public Product update(final Long id, final ProductDto productDto) {
        Assert.notNull(id, "Id must not be null");
        assertDto(productDto);
        final Product product = productRepository.getOne(id);
        setProductProperties(productDto, product);
        product.setUpdatedOn(LocalDateTime.now());
        productRepository.save(product);
        if (productDto.getStockQuantity() != null) {
            createOrResetStockForProduct(id, productDto.getStockQuantity());
        }
        return product;
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        Assert.notNull(id, "Id must not be null");
        final Product product = productRepository.getOne(id);
        product.setDeletedOn(LocalDateTime.now());
        productRepository.save(product);
    }

    private void setProductProperties(final ProductDto productDto, final Product product) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setType(productDto.getType());
        product.setPrice(productDto.getPrice());
    }

    private void createOrResetStockForProduct(final Long productId, final int quantity) {
        applicationEventPublisher.publishEvent(new ProductCreatedEvent(this, productId, quantity));
    }

    private void assertDto(final ProductDto productDto) {
        Assert.notNull(productDto, "Product Dto must not be null");
        Assert.notNull(productDto.getName(), "Product name must not be null");
        Assert.notNull(productDto.getPrice(), "Product price must not be null");
        Assert.notNull(productDto.getType(), "Product type must not be null");
    }

}
