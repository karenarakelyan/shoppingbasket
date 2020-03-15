package com.karen.shoppingbasket.facade.user.impl;

import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.restmodels.user.UserResponseModel;
import com.karen.shoppingbasket.restmodels.user.UserRole;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class UserMapperFacade {

    public UserResponseModel map(final User user) {
        return new UserResponseModel(user.getId(), user.getUsername(), UserRole.valueOf(user.getRole().name()));
    }

}
