package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import io.dropwizard.jackson.Jackson;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anton Kharenko
 */
public class ErrorResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        final ErrorResponse actualMessage = ErrorResponse.newBuilder()
                .message("User with such username already exists.")
                .build();

        final ErrorResponse expectedMessage = MAPPER.readValue(fixture("fixtures/ErrorResponse.json"), ErrorResponse.class);

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void serializesToJSON() throws Exception {
        final ErrorResponse actualMessage = ErrorResponse.newBuilder()
                .message("User with such username already exists.")
                .build();
        final String actualJson = MAPPER.writeValueAsString(actualMessage);

        final ErrorResponse expectedMessage = MAPPER.readValue(fixture("fixtures/ErrorResponse.json"), ErrorResponse.class);
        final String normalizedExpectedJson = MAPPER.writeValueAsString(expectedMessage);

        assertThat(actualJson).isEqualTo(normalizedExpectedJson);
    }

}
