package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public final class UserResponse {

	@JsonProperty("id")
	private String id;

	@JsonProperty("username")
    private String username;

	@JsonProperty("email")
	private String email;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("api_key")
    private String apiKey;

	@SuppressWarnings("unused")
	private UserResponse() {
		// Jackson deserialization
	}

    private UserResponse(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.apiKey = builder.apiKey;
    }

	public static Builder newBuilder() {
		return new Builder();
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
        return username;
    }

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

    public String getApiKey() {
		return apiKey;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserResponse that = (UserResponse) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(username, that.username) &&
				Objects.equals(email, that.email) &&
				Objects.equals(firstName, that.firstName) &&
				Objects.equals(lastName, that.lastName) &&
				Objects.equals(apiKey, that.apiKey);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email, firstName, lastName, apiKey);
	}

	@Override
	public String toString() {
		return "UserResponse{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", apiKey='" + apiKey + '\'' +
				'}';
	}

	public static final class Builder {

		private String id;
		private String username;
		private String email;
		private String firstName;
		private String lastName;
		private String apiKey;

		private Builder() {
		}

		public Builder userId(String id) {
			this.id = id;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}

		public UserResponse build() {
			return new UserResponse(this);
		}
	}
}
