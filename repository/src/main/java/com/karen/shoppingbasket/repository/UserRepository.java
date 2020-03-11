package com.karen.shoppingbasket.repository;

import com.karen.shoppingbasket.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karen Arakelyan
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
