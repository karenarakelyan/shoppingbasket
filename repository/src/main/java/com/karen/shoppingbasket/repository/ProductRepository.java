package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karen Arakelyan
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
