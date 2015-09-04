package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.api.LogInRequest;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.api.UpdateUserRequest;
import com.antonkharenko.booklib.api.UserResponse;
import com.antonkharenko.booklib.domain.User;

/**
 * @author Anton Kharenko
 */
public interface UserService {

    UserResponse signUp(SignUpRequest request);

    UserResponse logIn(LogInRequest request);

    UserResponse updateAccount(UpdateUserRequest request, User user);

}
