package com.antonkharenko.booklib.integration;

import com.antonkharenko.booklib.annotation.type.IntegrationTest;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.api.UpdateUserRequest;
import com.antonkharenko.booklib.api.UserResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Anton Kharenko
 */
@Category(IntegrationTest.class)
public class UserServiceIT extends AbstractServiceIT {

    @Before
    public void clearDb() {
        dropDatabase();
    }

    @Test
    public void testSignUp() {
        // Given
        final SignUpRequest signUpRequest = createSignUpRequest();

        // When
        final UserResponse userResponse = request("/api/account/signup", "POST", signUpRequest, UserResponse.class);

        // Then
        assertNotNull(userResponse);
        assertNotNull(userResponse.getId());
        assertNotNull(userResponse.getApiKey());
        assertEquals(signUpRequest.getUsername(), userResponse.getUsername());
        assertEquals(signUpRequest.getEmail(), userResponse.getEmail());
        assertEquals(signUpRequest.getFirstName(), userResponse.getFirstName());
        assertEquals(signUpRequest.getLastName(), userResponse.getLastName());
    }

    @Test
    public void testUpdateAccount() {
        // Given
        final UserResponse userResponse = registerUser();
        final UpdateUserRequest updateUserRequest = UpdateUserRequest.newBuilder()
                .username("updated_johndoe")
                .firstName("Updated_John")
                .lastName("Updated_Doe")
                .build();

        // When
        final UserResponse updatedUserResponse = request("/api/account", "POST", updateUserRequest, UserResponse.class, userResponse.getApiKey());

        // Then
        assertNotNull(updatedUserResponse);
        assertEquals(userResponse.getId(), updatedUserResponse.getId());
        assertEquals(userResponse.getApiKey(), updatedUserResponse.getApiKey());
        assertEquals(userResponse.getEmail(), updatedUserResponse.getEmail());
        assertEquals(updateUserRequest.getUsername(), updatedUserResponse.getUsername());
        assertEquals(updateUserRequest.getFirstName(), updatedUserResponse.getFirstName());
        assertEquals(updateUserRequest.getLastName(), updatedUserResponse.getLastName());
    }

}
