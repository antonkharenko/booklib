package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.api.*;
import com.google.common.base.Optional;
import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.repository.UserRepository;
import com.antonkharenko.booklib.services.exceptions.ConflictException;
import com.antonkharenko.booklib.services.exceptions.UnauthorizedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

/**
 * @author Anton Kharenko
 */
public class AccountServiceImplTest {

	@Mock private AuthService authService;
	@Mock private UserRepository userRepository;

	private AccountServiceImpl accountService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(userRepository, authService);
	}

	@Test
	public void testSignUpSuccessful() {
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
		final User expectedUser = new User(
				request.getUsername(),
				request.getPassword(),
				request.getEmail(),
				request.getFirstName(),
				request.getLastName(),
				expectedResponse.getApiKey());
		expectedUser.setId(expectedResponse.getId());

		// Stubbing
		when(userRepository.findByUsername(request.getUsername()))
				.thenReturn(Optional.absent());
		when(userRepository.findByEmail(request.getEmail()))
				.thenReturn(Optional.absent());
		when(userRepository.save(any(User.class)))
				.thenReturn(expectedUser);

		// When
		UserResponse actualResponse = accountService.signUp(request);

		// Then
		Assert.assertNotNull(actualResponse);
		Assert.assertEquals(expectedResponse, actualResponse);
		// Verify that all expected user data passed to repository save method
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
		verify(userRepository, times(1)).save(argument.capture());
		User actualUser = argument.getValue();
		Assert.assertNotNull(actualUser.getApiKey());
		Assert.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
		Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
		Assert.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
		Assert.assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
		Assert.assertEquals(expectedUser.getLastName(), actualUser.getLastName());
	}

	@Test(expected = ConflictException.class)
	public void testSignUpUsernameConflict() {
		// Given
		final SignUpRequest request = SignUpRequest.newBuilder()
				.username("johndoe").password("qwerty").email("johndoe@example.com").firstName("John").lastName("Doe")
				.build();

		// Stubbing
		when(userRepository.findByUsername(request.getUsername()))
				.thenReturn(Optional.of(new User()));

		// When
		accountService.signUp(request);
	}

	@Test(expected = ConflictException.class)
	public void testSignUpEmailConflict() {
		// Given
		final SignUpRequest request = SignUpRequest.newBuilder()
				.username("johndoe").password("qwerty").email("johndoe@example.com").firstName("John").lastName("Doe")
				.build();

		// Stubbing
		when(userRepository.findByUsername(request.getUsername()))
				.thenReturn(Optional.absent());
		when(userRepository.findByEmail(request.getEmail()))
				.thenReturn(Optional.of(new User()));

		// When
		accountService.signUp(request);
	}

	@Test
	public void testLogInSuccessful() {
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
		final User expectedUser = new User(
				expectedResponse.getUsername(),
				request.getPassword(),
				expectedResponse.getEmail(),
				expectedResponse.getFirstName(),
				expectedResponse.getLastName(),
				expectedResponse.getApiKey());
		expectedUser.setId(expectedResponse.getId());

		// Stubbing
		when(authService.authorizeByLoginAndPassword(request.getLogin(), request.getPassword()))
				.thenReturn(Optional.of(expectedUser));

		// When
		UserResponse actualResponse = accountService.logIn(request);

		// Then
		Assert.assertNotNull(actualResponse);
		Assert.assertEquals(expectedResponse, actualResponse);
	}

	@Test(expected = UnauthorizedException.class)
	public void testLogInUnauthorized() {
		// Given
		final LogInRequest request = LogInRequest.newBuilder().login("johndoe").password("qwerty").build();

		// Stubbing
		when(authService.authorizeByLoginAndPassword(request.getLogin(), request.getPassword()))
				.thenReturn(Optional.absent());

		// When
		accountService.logIn(request);
	}

}
