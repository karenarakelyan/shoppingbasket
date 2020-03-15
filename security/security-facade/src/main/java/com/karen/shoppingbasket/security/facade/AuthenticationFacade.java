package com.karen.shoppingbasket.security.facade;

import com.karen.shoppingbasket.restmodels.authentication.AuthenticationRequestModel;
import com.karen.shoppingbasket.restmodels.authentication.AuthenticationResponseModel;
import com.karen.shoppingbasket.restmodels.authentication.TokenAuthenticationRequestModel;

/**
 * @author Karen Arakelyan
 */

public interface AuthenticationFacade {

    AuthenticationResponseModel login(AuthenticationRequestModel authenticationRequestModel);

    AuthenticationResponseModel loginWithToken(TokenAuthenticationRequestModel tokenAuthenticationRequestModel);

    void logout(String tokenValue);

}
