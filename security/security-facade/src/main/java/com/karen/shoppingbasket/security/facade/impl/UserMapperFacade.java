package com.karen.shoppingbasket.security.facade.impl;

import com.karen.shoppingbasket.dto.user.UserDto;
import com.karen.shoppingbasket.entity.user.Role;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.restmodels.user.CreateNewUserRequestModel;
import com.karen.shoppingbasket.restmodels.user.UserResponseModel;
import com.karen.shoppingbasket.restmodels.user.UserRole;
import com.karen.shoppingbasket.security.core.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class UserMapperFacade {

    private final PasswordHelper passwordHelper;

    @Autowired
    public UserMapperFacade(final PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    public UserResponseModel map(final User user) {
        return new UserResponseModel(user.getId(), user.getUsername(), UserRole.valueOf(user.getRole().name()));
    }

    public UserDto generateUserDto(final CreateNewUserRequestModel createNewUserRequestModel) {
        final UserDto userDto = new UserDto();
        userDto.setUsername(createNewUserRequestModel.getEmail());
        userDto.setPassword(passwordHelper.createHashedPassword(createNewUserRequestModel.getPassword()));
        userDto.setName(createNewUserRequestModel.getName());
        userDto.setSurname(createNewUserRequestModel.getSurname());
        userDto.setBirthday(createNewUserRequestModel.getBirthday());
        userDto.setRole(Role.CUSTOMER);
        return userDto;
    }

}
