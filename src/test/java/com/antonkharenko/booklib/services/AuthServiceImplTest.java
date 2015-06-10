package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.repository.UserRepository;
import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author Anton Kharenko
 */
public class AuthServiceImplTest {

	@Mock private UserRepository userRepository;
	@InjectMocks private AuthServiceImpl authService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthorizeByApiKeySuccessful() {
		// Given
		final String apiKey = "1234567890";
		final User expectedUser = new User("johndoe", "qwerty", "johndoe@example.com", "John", "Doe", apiKey);
		expectedUser.setId("1");

		// Stubbing
		when(userRepository.findByApiKey(apiKey))
				.thenReturn(Optional.of(expectedUser));

		// When
		Optional<User> actualOptionalUser = authService.authorizeByApiKey(apiKey);

		// Then
		assertNotNull(actualOptionalUser);
		assertTrue(actualOptionalUser.isPresent());
		assertEquals(expectedUser, actualOptionalUser.get());
	}

	@Test
	public void testAuthorizeByApiKeyFailed() {
		// Given
		final String apiKey = "1234567890";

		// Stubbing
		when(userRepository.findByApiKey(apiKey))
				.thenReturn(Optional.absent());

		// When
		Optional<User> actualOptionalUser = authService.authorizeByApiKey(apiKey);

		// Then
		assertNotNull(actualOptionalUser);
		assertFalse(actualOptionalUser.isPresent());
	}

	@Test
	public void testAuthorizeByLoginAndPasswordWithUsername() {
		// Given
		final String login = "johndoe";
		final String password = "qwerty";
		final User expectedUser = new User(login, password, "johndoe@example.com", "John", "Doe", "1234567890");
		expectedUser.setId("1");

		// Stubbing
		when(userRepository.findByUsernameAndPassword(login, password))
				.thenReturn(Optional.of(expectedUser));

		// When
		Optional<User> actualOptionalUser = authService.authorizeByLoginAndPassword(login, password);

		// Then
		assertNotNull(actualOptionalUser);
		assertTrue(actualOptionalUser.isPresent());
		assertEquals(expectedUser, actualOptionalUser.get());
	}

	@Test
	public void testAuthorizeByLoginAndPasswordWithEmail() {
		// Given
		final String login = "johndoe@example.com";
		final String password = "qwerty";
		final User expectedUser = new User("johndoe", password, login, "John", "Doe", "1234567890");
		expectedUser.setId("1");

		// Stubbing
		when(userRepository.findByEmailAndPassword(login, password))
				.thenReturn(Optional.of(expectedUser));

		// When
		Optional<User> actualOptionalUser = authService.authorizeByLoginAndPassword(login, password);

		// Then
		assertNotNull(actualOptionalUser);
		assertTrue(actualOptionalUser.isPresent());
		assertEquals(expectedUser, actualOptionalUser.get());
	}

	@Test
	public void testAuthorizeByLoginAndPasswordFailed() {
		// Given
		final String login = "johndoe@example.com";
		final String password = "qwerty";

		// Stubbing
		when(userRepository.findByEmailAndPassword(login, password))
				.thenReturn(Optional.absent());

		// When
		Optional<User> actualOptionalUser = authService.authorizeByLoginAndPassword(login, password);

		// Then
		assertNotNull(actualOptionalUser);
		assertFalse(actualOptionalUser.isPresent());
	}

}
