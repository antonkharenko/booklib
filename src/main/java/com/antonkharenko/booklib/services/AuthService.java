package com.antonkharenko.booklib.services;

import com.google.common.base.Optional;

import com.antonkharenko.booklib.domain.User;

/**
 * @author Anton Kharenko
 */
public interface AuthService {

    Optional<User> authorizeByLoginAndPassword(String login, String password);

    Optional<User> authorizeByApiKey(String apiKey);

}
