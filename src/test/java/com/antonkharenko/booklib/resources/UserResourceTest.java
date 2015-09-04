package com.antonkharenko.booklib.resources;

import com.antonkharenko.booklib.api.LogInRequest;
import com.antonkharenko.booklib.api.SignUpRequest;
import com.antonkharenko.booklib.api.UserResponse;
import com.antonkharenko.booklib.services.UserService;
import com.antonkharenko.booklib.services.exceptions.ConflictException;
import com.antonkharenko.booklib.services.exceptions.UnauthorizedException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Anton Kharenko
 */
public class UserResourceTest {

    @Mock
    private ExceptionHandler exceptionHandler;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserResource userResource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUp() {
        // Given
        final SignUpRequest request = SignUpRequest.newBuilder()
                .username("johndoe").password("qwerty").email("johndoe@example.com").firstName("John").lastName("Doe")
                .build();
        final UserResponse expectedResponse = UserResponse.newBuilder()
                .id("1")
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .apiKey("1234567890")
                .build();

        // Stubbing
        when(userService.signUp(request))
                .thenReturn(expectedResponse);

        // When
        Object actualResponse = userResource.signUp(request);

        // Then
        assertNotNull(actualResponse);
        assertTrue(actualResponse instanceof UserResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testSignUpError() {
        // Given
        final SignUpRequest request = SignUpRequest.newBuilder()
                .username("johndoe").password("qwerty").email("johndoe@example.com").firstName("John").lastName("Doe")
                .build();
        final ConflictException error = new ConflictException("User already exists");
        final Response expectedErrorResponse = Response.status(Response.Status.CONFLICT).build();

        // Stubbing
        when(userService.signUp(request))
                .thenThrow(error);
        when(exceptionHandler.handleException(error))
                .thenReturn(expectedErrorResponse);

        // When
        Object actualErrorResponse = userResource.signUp(request);

        // Then
        assertNotNull(actualErrorResponse);
        assertTrue(actualErrorResponse instanceof Response);
        assertEquals(expectedErrorResponse, actualErrorResponse);
    }

    @Test
    public void testLogIn() {
        // Given
        final LogInRequest request = LogInRequest.newBuilder().login("johndoe").password("qwerty").build();
        final UserResponse expectedResponse = UserResponse.newBuilder()
                .id("1")
                .username(request.getLogin())
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .apiKey("1234567890")
                .build();

        // Stubbing
        when(userService.logIn(request))
                .thenReturn(expectedResponse);

        // When
        Object actualResponse = userResource.logIn(request);

        // Then
        assertNotNull(actualResponse);
        assertTrue(actualResponse instanceof UserResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testLogInError() {
        // Given
        final LogInRequest request = LogInRequest.newBuilder().login("johndoe").password("qwerty").build();
        final UnauthorizedException error = new UnauthorizedException("Wrong login or password");
        final Response expectedErrorResponse = Response.status(Response.Status.UNAUTHORIZED).build();

        // Stubbing
        when(userService.logIn(request))
                .thenThrow(error);
        when(exceptionHandler.handleException(error))
                .thenReturn(expectedErrorResponse);

        // When
        Object actualErrorResponse = userResource.logIn(request);

        // Then
        assertNotNull(actualErrorResponse);
        assertTrue(actualErrorResponse instanceof Response);
        assertEquals(expectedErrorResponse, actualErrorResponse);
    }

}
