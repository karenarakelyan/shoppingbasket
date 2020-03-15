package com.karen.shoppingbasket.security.facade;

import com.karen.shoppingbasket.restmodels.user.CreateNewUserRequestModel;

/**
 * @author Karen Arakelyan
 */

public interface UserFacade {

    Long createUser(CreateNewUserRequestModel createNewUserRequestModel);

}
