package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getByName(final String name);

    List<Product> findAllByType(final String type);

    List<Product> findAllByOrderByPriceDesc();

}
