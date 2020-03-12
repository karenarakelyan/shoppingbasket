package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Karen Arakelyan
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> getByUserId(final Long customerId);

}
