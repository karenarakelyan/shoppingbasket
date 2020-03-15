package com.karen.shoppingbasket.services;

import com.karen.shoppingbasket.dto.user.UserDto;
import com.karen.shoppingbasket.entity.user.User;

/**
 * @author Karen Arakelyan
 */

public interface UserService {

    Long create(UserDto userDto);

    User findById(Long id);

    User findByUsername(String username);

}
