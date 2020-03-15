package com.karen.shoppingbasket.rest.resources.user;

import com.karen.shoppingbasket.facade.order.OrderFacade;
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

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    private final UserFacade userFacade;

    private final OrderFacade orderFacade;

    @Autowired
    public UserResource(final UserFacade userFacade, final OrderFacade orderFacade) {
        this.userFacade = userFacade;
        this.orderFacade = orderFacade;
    }

    @RequestMapping(name = "Create User", method = RequestMethod.POST)
    public Long createUser(@RequestBody final CreateNewUserRequestModel createNewUserRequestModel) {
        return userFacade.createUser(createNewUserRequestModel);
    }


}
