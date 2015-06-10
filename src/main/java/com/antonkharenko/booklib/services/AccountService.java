package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.api.LogInRequest;
import com.antonkharenko.booklib.api.UpdateAccountRequest;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.api.UserResponse;

/**
 * @author Anton Kharenko
 */
public interface AccountService {

	UserResponse signUp(SignUpRequest request);

	UserResponse logIn(LogInRequest request);

	UserResponse updateAccount(UpdateAccountRequest request, User user);

}
