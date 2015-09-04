package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import io.dropwizard.jackson.Jackson;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anton Kharenko
 */
public class LogInRequestTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        final LogInRequest actualMessage = LogInRequest.newBuilder().login("johndoe").password("qwerty").build();

        final LogInRequest expectedMessage = MAPPER.readValue(fixture("fixtures/LogInRequest.json"), LogInRequest.class);

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void serializesToJSON() throws Exception {
        final LogInRequest actualMessage = LogInRequest.newBuilder().login("johndoe").password("qwerty").build();
        final String actualJson = MAPPER.writeValueAsString(actualMessage);

        final LogInRequest expectedMessage = MAPPER.readValue(fixture("fixtures/LogInRequest.json"), LogInRequest.class);
        final String normalizedExpectedJson = MAPPER.writeValueAsString(expectedMessage);

        assertThat(actualJson).isEqualTo(normalizedExpectedJson);
    }

}
