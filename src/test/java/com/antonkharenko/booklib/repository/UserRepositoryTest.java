package com.antonkharenko.booklib.repository;

import com.antonkharenko.booklib.annotation.type.IntegrationTest;
import com.antonkharenko.booklib.domain.User;
import com.google.common.base.Optional;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.io.IOException;


import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class UserRepositoryTest extends BaseRepositoryTest {

	private static UserRepository userRepository;

	@BeforeClass
	public static void initRepository() {
		userRepository = (UserRepository) applicationContext.getBean("userRepository");
	}

	@Before
	public void setUp() throws IOException {
		userRepository.deleteAll();
	}

	@After
	public void clearDb() {
		userRepository.deleteAll();
	}

	@Test
	public void testSaveAndFindByApiKeySuccess() {
		// Given
		final User expectedUser = new User("johndoe", "qwerty", "john@doe.com", "John", "Doe", "1234567890");

		// When
		userRepository.save(expectedUser);
		Optional<User> optionalUser = userRepository.findByApiKey(expectedUser.getApiKey());

		// Then
		assertNotNull(optionalUser);
		assertTrue(optionalUser.isPresent());
		User actualUser = optionalUser.get();
		assertNotNull(actualUser.getId());
		assertEquals(expectedUser.getUsername(), actualUser.getUsername());
		assertEquals(expectedUser.getPassword(), actualUser.getPassword());
		assertEquals(expectedUser.getEmail(), actualUser.getEmail());
		assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
		assertEquals(expectedUser.getLastName(), actualUser.getLastName());
	}
}
