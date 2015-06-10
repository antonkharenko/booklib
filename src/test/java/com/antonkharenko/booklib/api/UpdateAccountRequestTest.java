package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anton Kharenko
 */
public class UpdateAccountRequestTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void deserializesFromJSON() throws Exception {
		final UpdateAccountRequest actualMessage = UpdateAccountRequest.newBuilder()
				.username("johndoe")
				.firstName("John")
				.lastName("Doe")
				.build();

		final UpdateAccountRequest expectedMessage = MAPPER.readValue(fixture("fixtures/UpdateAccountRequest.json"), UpdateAccountRequest.class);

		assertThat(actualMessage).isEqualTo(expectedMessage);
	}

	@Test
	public void serializesToJSON() throws Exception {
		final UpdateAccountRequest actualMessage = UpdateAccountRequest.newBuilder()
				.username("johndoe")
				.firstName("John")
				.lastName("Doe")
				.build();
		final String actualJson = MAPPER.writeValueAsString(actualMessage);

		final UpdateAccountRequest expectedMessage = MAPPER.readValue(fixture("fixtures/UpdateAccountRequest.json"), UpdateAccountRequest.class);
		final String normalizedExpectedJson = MAPPER.writeValueAsString(expectedMessage);

		assertThat(actualJson).isEqualTo(normalizedExpectedJson);
	}

}
