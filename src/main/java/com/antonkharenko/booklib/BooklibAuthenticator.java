package com.antonkharenko.booklib;

import com.google.common.base.Optional;

import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * @author Anton Kharenko
 */
@Component("authenticator")
public class BooklibAuthenticator implements Authenticator<BasicCredentials, User> {

    @Autowired
    private AuthService authService;

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        String apiKey = basicCredentials.getUsername();
        if (apiKey != null && !apiKey.isEmpty()) {
            return authService.authorizeByApiKey(apiKey);
        }
        return Optional.absent();
    }
}
