package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.repository.UserRepository;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Anton Kharenko
 */
@Component
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;

	@Autowired
	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> authorizeByLoginAndPassword(String login, String password) {
		return login.contains("@") ?
				userRepository.findByEmailAndPassword(login, password) :
				userRepository.findByUsernameAndPassword(login, password);
	}

	@Override
	public Optional<User> authorizeByApiKey(String apiKey) {
		return userRepository.findByApiKey(apiKey);
	}

}
