package com.karen.shoppingbasket.services;

/**
 * @author Karen Arakelyan
 */

import com.karen.shoppingbasket.dto.product.ProductDto;
import com.karen.shoppingbasket.entity.product.Product;

import java.util.List;

public interface ProductService {

    Product findOne(Long id);

    List<Product> findAll(Boolean sortByPrice);

    Product findByName(String name);

    List<Product> findByType(String type);

    Long create(ProductDto productDto);

    Product update(Long id, ProductDto productDto);

    void delete(Long id);

}
