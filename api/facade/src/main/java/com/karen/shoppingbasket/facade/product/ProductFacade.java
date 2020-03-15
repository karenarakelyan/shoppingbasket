package com.karen.shoppingbasket.facade.product;

import com.karen.shoppingbasket.restmodels.product.CreateProductModel;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.UpdateProductModel;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

public interface ProductFacade {

    Long createProduct(CreateProductModel createProductModel);

    ProductInformationResponseModel updateProduct(Long id, UpdateProductModel updateProductModel);

    ProductInformationResponseModel getById(Long id);

    List<ProductInformationResponseModel> findAll();

    void delete(Long id);

}
