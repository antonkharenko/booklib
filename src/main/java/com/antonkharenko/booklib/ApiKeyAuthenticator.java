package com.antonkharenko.booklib;

import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.services.AuthService;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Anton Kharenko
 */
@Component("authenticator")
public class ApiKeyAuthenticator implements Authenticator<BasicCredentials, User> {

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
