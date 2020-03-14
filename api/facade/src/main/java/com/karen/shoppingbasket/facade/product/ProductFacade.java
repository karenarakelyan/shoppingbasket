package com.karen.shoppingbasket.facade.product;

import com.karen.shoppingbasket.restmodels.product.CreateProductModel;
import com.karen.shoppingbasket.restmodels.product.ProductInformationResponseModel;
import com.karen.shoppingbasket.restmodels.product.UpdateProductModel;

/**
 * @author Karen Arakelyan
 */

public interface ProductFacade {

    Long createProduct(CreateProductModel createProductModel);

    ProductInformationResponseModel updateProduct(Long id, UpdateProductModel updateProductModel);

    ProductInformationResponseModel getById(Long id);

    void delete(Long id);

}
