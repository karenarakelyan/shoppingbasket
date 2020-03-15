package com.karen.shoppingbasket.security.facade.impl;

import com.karen.shoppingbasket.restmodels.authentication.AuthenticationRequestModel;
import com.karen.shoppingbasket.restmodels.authentication.AuthenticationResponseModel;
import com.karen.shoppingbasket.restmodels.authentication.TokenAuthenticationRequestModel;
import com.karen.shoppingbasket.security.dto.SuccessfullyAuthenticatedDto;
import com.karen.shoppingbasket.security.facade.AuthenticationFacade;
import com.karen.shoppingbasket.security.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Karen Arakelyan
 */

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationService authenticationService;

    private final AuthenticationMapperFacade authenticationMapperFacade;

    @Autowired
    public AuthenticationFacadeImpl(final AuthenticationService authenticationService, final AuthenticationMapperFacade authenticationMapperFacade) {
        this.authenticationService = authenticationService;
        this.authenticationMapperFacade = authenticationMapperFacade;
    }

    @Override
    public AuthenticationResponseModel login(final AuthenticationRequestModel authenticationRequestModel) {
        final SuccessfullyAuthenticatedDto successfullyAuthenticatedDto = authenticationService.authenticateWithCredentials(authenticationRequestModel.getEmail(), authenticationRequestModel.getPassword());
        return authenticationMapperFacade.map(successfullyAuthenticatedDto);
    }

    @Override
    public AuthenticationResponseModel loginWithToken(final TokenAuthenticationRequestModel tokenAuthenticationRequestModel) {
        final SuccessfullyAuthenticatedDto successfullyAuthenticatedDto = authenticationService.authenticateWithToken(tokenAuthenticationRequestModel.getToken());
        return authenticationMapperFacade.map(successfullyAuthenticatedDto);
    }

    @Override
    public void logout(final String tokenValue) {
        authenticationService.logoutWithToken(tokenValue);
    }
}
