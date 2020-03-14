package com.karen.shoppingbasket.facade.product.impl;

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.ProductModel;
import com.karen.shoppingbasket.services.StockMutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public ProductDto generateProductDto(final ProductModel productModel) {
        final ProductDto productDto = new ProductDto();
        productDto.setName(productModel.getName());
        productDto.setDescription(productModel.getDescription());
        productDto.setPrice(productModel.getPrice());
        productDto.setType(productModel.getType());
        return productDto;
    }

    public ProductInformationResponseModel generateResponseModel(final Product product) {
        final ProductInformationResponseModel productInformationResponseModel = new ProductInformationResponseModel();
        productInformationResponseModel.setId(product.getId());
        productInformationResponseModel.setName(product.getName());
        productInformationResponseModel.setDescription(product.getDescription());
        productInformationResponseModel.setType(product.getType());
        productInformationResponseModel.setPrice(product.getPrice());
        productInformationResponseModel.setCount(stockMutationService.calculateStock(product.getId()));
        return productInformationResponseModel;
    }

}
