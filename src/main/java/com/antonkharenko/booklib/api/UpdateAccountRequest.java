package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public final class UpdateAccountRequest {

	@NotEmpty
	@Length(min = 3, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9\\-_\\.]{3,20}$", message = "must contain only numbers and letters")
	@JsonProperty("username")
	private String username;

	@NotEmpty
	@JsonProperty("first_name")
	private String firstName;

	@NotEmpty
	@JsonProperty("last_name")
	private String lastName;

	@SuppressWarnings("unused")
	private UpdateAccountRequest() {
		// Jackson deserialization
	}

	private UpdateAccountRequest(Builder builder) {
		this.username = builder.username;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
	}

	public static UpdateAccountRequest.Builder newBuilder() {
		return new Builder();
	}

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UpdateAccountRequest that = (UpdateAccountRequest) o;
		return Objects.equals(username, that.username) &&
				Objects.equals(firstName, that.firstName) &&
				Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, firstName, lastName);
	}

	@Override
	public String toString() {
		return "UpdateAccountRequest{" +
				"username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}

	public static final class Builder {

		private String username;
		private String firstName;
		private String lastName;

		private Builder() {
		}

		public Builder username(String username) {
			this.username = username;
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

		public UpdateAccountRequest build() {
			return new UpdateAccountRequest(this);
		}
	}
}
