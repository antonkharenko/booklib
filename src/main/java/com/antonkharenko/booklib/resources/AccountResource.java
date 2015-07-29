package com.antonkharenko.booklib.resources;

import com.antonkharenko.booklib.api.LogInRequest;
import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.services.AccountService;
import com.codahale.metrics.annotation.Timed;
import com.antonkharenko.booklib.api.UpdateAccountRequest;
import io.dropwizard.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Anton Kharenko
 */
@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AccountResource {

    private final AccountService accountService;
    private final ExceptionHandler exceptionHandler;

    @Autowired
    public AccountResource(AccountService accountService, ExceptionHandler exceptionHandler) {
        this.accountService = accountService;
        this.exceptionHandler = exceptionHandler;
    }

    @POST
    @Path("/signup")
    @Timed
    public Object signUp(@Valid SignUpRequest request) {
        try {
            return accountService.signUp(request);
        } catch (Exception e) {
			return exceptionHandler.handleException(e);
        }
    }

    @POST
    @Path("/login")
    @Timed
    public Object logIn(@Valid LogInRequest request) {
        try {
            return accountService.logIn(request);
        } catch (Exception e) {
			return exceptionHandler.handleException(e);
        }
    }

    @POST
    @Path("/logout")
    @Timed
    public Object logOut(@Auth User user) {
        return Response.noContent().build();
    }

    @POST
    @Timed
    public Object updateAccount(
            @Valid UpdateAccountRequest request,
            @Auth User user) {
        try {
            return accountService.updateAccount(request, user);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

}
