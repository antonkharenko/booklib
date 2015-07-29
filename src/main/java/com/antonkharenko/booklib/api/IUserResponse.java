package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * @author Anton Kharenko
 */
@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = UserResponse.class)
@JsonDeserialize(as = UserResponse.class)
public interface IUserResponse {

	String getId();

	String getUsername();

	String getEmail();

	@JsonProperty("first_name")
	String getFirstName();

	@JsonProperty("last_name")
	String getLastName();

	@JsonProperty("api_key")
	String getApiKey();

}
