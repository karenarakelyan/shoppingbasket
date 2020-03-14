package com.karen.shoppingbasket.services;

/**
 * @author Karen Arakelyan
 */

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;

public interface ProductService {

    Product findOne(Long id);

    Long create(ProductDto productDto);

    Product update(Long id, ProductDto productDto);

    void delete(Long id);

}
