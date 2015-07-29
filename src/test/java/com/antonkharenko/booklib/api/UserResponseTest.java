package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anton Kharenko
 */
public class UserResponseTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void deserializesFromJSON() throws Exception {
		final UserResponse actualMessage = UserResponse.newBuilder()
				.id("1")
				.username("johndoe")
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@example.com")
				.apiKey("1234567890")
				.build();

		final UserResponse expectedMessage = MAPPER.readValue(fixture("fixtures/UserResponse.json"), UserResponse.class);

		assertThat(actualMessage).isEqualTo(expectedMessage);
	}

	@Test
	public void serializesToJSON() throws Exception {
		final UserResponse actualMessage = UserResponse.newBuilder()
				.id("1")
				.username("johndoe")
				.firstName("John")
				.lastName("Doe")
				.email("johndoe@example.com")
				.apiKey("1234567890")
				.build();
		final String actualJson = MAPPER.writeValueAsString(actualMessage);

		final UserResponse expectedMessage = MAPPER.readValue(fixture("fixtures/UserResponse.json"), UserResponse.class);
		final String normalizedExpectedJson = MAPPER.writeValueAsString(expectedMessage);

		assertThat(actualJson).isEqualTo(normalizedExpectedJson);
	}

}
