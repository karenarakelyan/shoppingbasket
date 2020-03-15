package com.karen.shoppingbasket.facade.product.impl;

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.restmodels.product.CreateProductModel;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.UpdateProductModel;
import com.karen.shoppingbasket.services.StockMutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Karen Arakelyan
 */

@Component
public class ProductMapperFacade {

    private final StockMutationService stockMutationService;

    @Autowired
    public ProductMapperFacade(final StockMutationService stockMutationService) {
        this.stockMutationService = stockMutationService;
    }

    public ProductDto generateProductDto(final CreateProductModel productModel) {
        return getProductDto(productModel.getName(), productModel.getDescription(), productModel.getPrice(), productModel.getType(), productModel.getStockQuantity());
    }

    public ProductDto generateProductDto(final UpdateProductModel productModel) {
        return getProductDto(productModel.getName(), productModel.getDescription(), productModel.getPrice(), productModel.getType(), productModel.getStockQuantity());
    }

    private ProductDto getProductDto(final String name, final String description, final BigDecimal price, final String type, final Integer stockQuantity) {
        final ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setPrice(price);
        productDto.setType(type);
        productDto.setStockQuantity(stockQuantity);
        return productDto;
    }

    public ProductInformationResponseModel generateResponseModel(final Product product) {
        final ProductInformationResponseModel productInformationResponseModel = new ProductInformationResponseModel();
        productInformationResponseModel.setId(product.getId());
        productInformationResponseModel.setName(product.getName());
        productInformationResponseModel.setDescription(product.getDescription());
        productInformationResponseModel.setType(product.getType());
        productInformationResponseModel.setPrice(product.getPrice());
        productInformationResponseModel.setStockQuantity(stockMutationService.calculateStock(product.getId()));
        return productInformationResponseModel;
    }

}
