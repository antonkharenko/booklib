package com.antonkharenko.booklib.services;

import com.google.common.base.Optional;

import com.antonkharenko.booklib.api.LogInRequest;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.api.UpdateUserRequest;
import com.antonkharenko.booklib.api.UserResponse;
import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.repository.UserRepository;
import com.antonkharenko.booklib.services.exceptions.ConflictException;
import com.antonkharenko.booklib.services.exceptions.UnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Anton Kharenko
 */
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthService authService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public UserResponse signUp(SignUpRequest request) {
        checkConflictByUsername(request.getUsername());
        checkConflictByEmail(request.getEmail());

        // Create new user entity
        String apiKey = generateApiKey();
        User user = new User(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                apiKey
        );
        user = userRepository.save(user);

        return toUserResponse(user);
    }

    @Override
    public UserResponse logIn(LogInRequest request) {
        Optional<User> optionalUser = authService.authorizeByLoginAndPassword(request.getLogin(), request.getPassword());
        if (!optionalUser.isPresent())
            throw new UnauthorizedException("Wrong login or password.");

        return toUserResponse(optionalUser.get());
    }

    @Override
    public UserResponse updateAccount(UpdateUserRequest request, User user) {
        if (!user.getUsername().equals(request.getUsername()))
            checkConflictByUsername(request.getUsername());

        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = userRepository.save(user);
        return toUserResponse(savedUser);
    }

    private String generateApiKey() {
        return UUID.randomUUID().toString();
    }

    private void checkConflictByUsername(String username) {
        Optional<User> conflictUser = userRepository.findByUsername(username);
        if (conflictUser.isPresent())
            throw new ConflictException("User with such username already exists.");
    }

    private void checkConflictByEmail(String email) {
        // Check if user with given email exists
        Optional<User> conflictUser = userRepository.findByEmail(email);
        if (conflictUser.isPresent())
            throw new ConflictException("User with such email already exists.");
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.newBuilder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .apiKey(user.getApiKey())
                .build();
    }

}
