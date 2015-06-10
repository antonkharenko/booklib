package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author Anton Kharenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndustryIdentifier {

	@JsonProperty
	private String type;

	@JsonProperty
	private String identifier;

	@SuppressWarnings("unused")
	private IndustryIdentifier() {
		// Jackson deserialization
	}

	private IndustryIdentifier(Builder builder) {
		this.type = builder.type;
		this.identifier = builder.identifier;
	}

	public static IndustryIdentifier.Builder newBuilder() {
		return new Builder();
	}

	/** Identifier type. Possible values are ISBN_10, ISBN_13, ISSN and OTHER. */
	public String getType() {
		return type;
	}

	/** Industry specific volume identifier. */
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		IndustryIdentifier that = (IndustryIdentifier) o;
		return Objects.equals(type, that.type) &&
				Objects.equals(identifier, that.identifier);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, identifier);
	}

	@Override
	public String toString() {
		return "IndustryIdentifier{" +
				"type='" + type + '\'' +
				", identifier='" + identifier + '\'' +
				'}';
	}

	public static final class Builder {

		private String type;
		private String identifier;

		private Builder() {
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder identifier(String identifier) {
			this.identifier = identifier;
			return this;
		}

		public IndustryIdentifier build() {
			return new IndustryIdentifier(this);
		}
	}
}
