package com.karen.shoppingbasket.rest.resources.user;

import com.karen.shoppingbasket.restmodels.user.CreateNewUserRequestModel;
import com.karen.shoppingbasket.security.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Karen Arakelyan
 */

@RestController(value = "/user")
public class UserResource {

    private final UserFacade userFacade;

    @Autowired
    public UserResource(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(name = "Create User", method = RequestMethod.POST)
    public Long createUser(@RequestBody final CreateNewUserRequestModel createNewUserRequestModel) {
        return userFacade.createUser(createNewUserRequestModel);
    }


}
