package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public final class LogInRequest {

	@NotEmpty
	@JsonProperty("login")
    private String login;

	@NotEmpty
	@JsonProperty("password")
    private String password;

	@SuppressWarnings("unused")
	private LogInRequest() {
		// Jackson deserialization
	}

	private LogInRequest(Builder builder) {
		this.login = builder.login;
		this.password = builder.password;
	}

	public static LogInRequest.Builder newBuilder() {
		return new Builder();
	}

	public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LogInRequest that = (LogInRequest) o;
		return Objects.equals(login, that.login) &&
				Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public String toString() {
		return "LogInRequest{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public static final class Builder {

		private String login;
		private String password;

		private Builder() {
		}

		public Builder login(String login) {
			this.login = login;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public LogInRequest build() {
			return new LogInRequest(this);
		}
	}
}
