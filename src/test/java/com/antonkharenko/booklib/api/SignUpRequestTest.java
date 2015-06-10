package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anton Kharenko
 */
public class SignUpRequestTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void deserializesFromJSON() throws Exception {
		final SignUpRequest actualMessage = SignUpRequest.newBuilder()
				.username("johndoe").password("qwerty").email("johndoe@example.com").firstName("John").lastName("Doe")
				.build();

		final SignUpRequest expectedMessage = MAPPER.readValue(fixture("fixtures/SignUpRequest.json"), SignUpRequest.class);

		assertThat(actualMessage).isEqualTo(expectedMessage);
	}

	@Test
	public void serializesToJSON() throws Exception {
		final SignUpRequest actualMessage = SignUpRequest.newBuilder()
				.username("johndoe").password("qwerty").email("johndoe@example.com").firstName("John").lastName("Doe")
				.build();
		final String actualJson = MAPPER.writeValueAsString(actualMessage);

		final SignUpRequest expectedMessage = MAPPER.readValue(fixture("fixtures/SignUpRequest.json"), SignUpRequest.class);
		final String normalizedExpectedJson = MAPPER.writeValueAsString(expectedMessage);

		assertThat(actualJson).isEqualTo(normalizedExpectedJson);
	}

}
