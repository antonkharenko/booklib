package com.antonkharenko.booklib.repository;

import com.google.common.base.Optional;

import com.antonkharenko.booklib.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Anton Kharenko
 */
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByApiKey(String apiKey);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
