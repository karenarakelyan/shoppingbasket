package com.karen.shoppingbasket.rest.resources.authentication;

import com.karen.shoppingbasket.restmodels.authentication.AuthenticationRequestModel;
import com.karen.shoppingbasket.restmodels.authentication.AuthenticationResponseModel;
import com.karen.shoppingbasket.restmodels.authentication.TokenAuthenticationRequestModel;
import com.karen.shoppingbasket.security.facade.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Karen Arakelyan
 */

@RestController(value = "/authentication")
public class AuthenticationResource {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public AuthenticationResource(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @ResponseBody
    @PostMapping("/authentication/login")
    public AuthenticationResponseModel login(@RequestBody @Valid final AuthenticationRequestModel request) {
        return authenticationFacade.login(request);
    }

    @ResponseBody
    @PostMapping("/authentication/login-token")
    public AuthenticationResponseModel loginWithToken(@RequestBody @Valid final TokenAuthenticationRequestModel request) {
        return authenticationFacade.loginWithToken(request);
    }

    @GetMapping("/authentication/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") final String tokenValue) {
        authenticationFacade.logout(tokenValue);
        return ResponseEntity.ok(Optional.empty());
    }

}
