package com.karen.shoppingbasket.security.facade.impl;

import com.karen.shoppingbasket.restmodels.authentication.AuthenticationResponseModel;
import com.karen.shoppingbasket.restmodels.user.UserResponseModel;
import com.karen.shoppingbasket.security.dto.SuccessfullyAuthenticatedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class AuthenticationMapperFacade {

    private final UserMapperFacade userMapperFacade;

    @Autowired
    public AuthenticationMapperFacade(final UserMapperFacade userMapperFacade) {
        this.userMapperFacade = userMapperFacade;
    }

    AuthenticationResponseModel map(final SuccessfullyAuthenticatedDto successfullyAuthenticatedDto) {
        final UserResponseModel userResponseModel = userMapperFacade.map(successfullyAuthenticatedDto.getUser());
        return new AuthenticationResponseModel(userResponseModel, successfullyAuthenticatedDto.getToken(), successfullyAuthenticatedDto.getExpirationDate());
    }


}
