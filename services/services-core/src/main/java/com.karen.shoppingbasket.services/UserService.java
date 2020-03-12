package com.karen.shoppingbasket.services;

import com.karen.shoppingbasket.entity.user.User;

/**
 * @author Karen Arakelyan
 */

public interface UserService {

    User findById(Long id);

}
