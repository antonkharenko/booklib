package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anton Kharenko
 */
public final class ErrorResponse {

	@JsonProperty("message")
	private String message;

	private ErrorResponse() {
		// Jackson deserialization
	}

	private ErrorResponse(Builder builder) {
		this.message = builder.message;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ErrorResponse that = (ErrorResponse) o;

		if (message != null ? !message.equals(that.message) : that.message != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return message != null ? message.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ErrorResponse{" +
				"message='" + message + '\'' +
				'}';
	}

	public static final class Builder {

		private String message;

		private Builder() {
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public ErrorResponse build() {
			return new ErrorResponse(this);
		}
	}
}
