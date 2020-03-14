package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.stock.StockMutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Karen Arakelyan
 */

@Repository
public interface StockMutationRepository extends JpaRepository<StockMutation, Long> {

    @Query("SELECT SUM(sm.mutationCount) FROM StockMutation sm where sm.product.id = ?1 AND sm.id >= ?2")
    Integer calculateSumByProductId(Long productId, Long id);

    @Query("SELECT sm FROM StockMutation sm where sm.product.id = ?1 AND sm.mutationType = 'RESET' ORDER BY id DESC")
    StockMutation getLastResetMutationForProduct(Long productId);

}
