package com.karen.shoppingbasket.security.facade.impl;

import com.karen.shoppingbasket.dto.user.UserDto;
import com.karen.shoppingbasket.restmodels.user.CreateNewUserRequestModel;
import com.karen.shoppingbasket.security.facade.UserFacade;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    private final UserMapperFacade userMapperFacade;

    @Autowired
    public UserFacadeImpl(final UserService userService, final UserMapperFacade userMapperFacade) {
        this.userService = userService;
        this.userMapperFacade = userMapperFacade;
    }

    @Override
    public Long createUser(final CreateNewUserRequestModel createNewUserRequestModel) {
        final UserDto userDto = userMapperFacade.generateUserDto(createNewUserRequestModel);
        return userService.create(userDto);
    }
}
