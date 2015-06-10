package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.domain.User;
import com.google.common.base.Optional;

/**
 * @author Anton Kharenko
 */
public interface AuthService {

	Optional<User> authorizeByLoginAndPassword(String login, String password);

	Optional<User> authorizeByApiKey(String apiKey);

}
