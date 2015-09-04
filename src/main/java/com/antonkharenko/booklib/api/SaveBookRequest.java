package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public class SaveBookRequest {

    @NotEmpty
    @JsonProperty("search_book_id")
    private String searchBookId;

    @SuppressWarnings("unused")
    private SaveBookRequest() {
        // Jackson deserialization
    }

    private SaveBookRequest(Builder builder) {
        this.searchBookId = builder.searchBookId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getSearchBookId() {
        return searchBookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SaveBookRequest that = (SaveBookRequest) o;
        return Objects.equals(searchBookId, that.searchBookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchBookId);
    }

    @Override
    public String toString() {
        return "SaveBookRequest{" +
                "searchBookId='" + searchBookId + '\'' +
                '}';
    }

    public static final class Builder {

        private String searchBookId;

        private Builder() {
        }

        public Builder searchBookId(String searchBookId) {
            this.searchBookId = searchBookId;
            return this;
        }

        public SaveBookRequest build() {
            return new SaveBookRequest(this);
        }
    }

}
