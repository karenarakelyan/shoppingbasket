package com.karen.shoppingbasket.facade.product.impl;

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.facade.product.ProductFacade;
import com.karen.shoppingbasket.restmodels.product.CreateProductModel;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.UpdateProductModel;
import com.karen.shoppingbasket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;

    private final ProductMapperFacade productMapperFacade;

    @Autowired
    public ProductFacadeImpl(final ProductService productService, final ProductMapperFacade productMapperFacade) {
        this.productService = productService;
        this.productMapperFacade = productMapperFacade;
    }

    @Override
    public Long createProduct(final CreateProductModel createProductModel) {
        final ProductDto productDto = productMapperFacade.generateProductDto(createProductModel);
        return productService.create(productDto);
    }

    @Override
    public ProductInformationResponseModel updateProduct(final Long id, final UpdateProductModel updateProductModel) {
        final ProductDto productDto = productMapperFacade.generateProductDto(updateProductModel);
        final Product updatedProduct = productService.update(id, productDto);
        return productMapperFacade.generateResponseModel(updatedProduct);
    }

    @Override
    public ProductInformationResponseModel getById(final Long id) {
        final Product product = productService.findOne(id);
        return productMapperFacade.generateResponseModel(product);
    }

    @Override
    public void delete(final Long id) {
        productService.delete(id);
    }



}
