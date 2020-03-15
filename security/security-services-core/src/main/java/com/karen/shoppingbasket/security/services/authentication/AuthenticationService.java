package com.karen.shoppingbasket.security.services.authentication;

import com.karen.shoppingbasket.security.dto.SuccessfullyAuthenticatedDto;

/**
 * @author Karen Arakelyan
 */

public interface AuthenticationService {

    SuccessfullyAuthenticatedDto authenticateWithCredentials(String username, String password);

    SuccessfullyAuthenticatedDto authenticateWithToken(String tokenValue);

    void logoutWithToken(String tokenValue);

}
