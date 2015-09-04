package com.antonkharenko.booklib.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * @author Anton Kharenko
 */
public class BookSearchResult {

    @JsonProperty("total_books")
    private Integer totalBooks;

    @JsonProperty("books")
    private List<BookResponse> books;

    @SuppressWarnings("unused")
    private BookSearchResult() {
        // Jackson deserialization
    }

    private BookSearchResult(Builder builder) {
        this.totalBooks = builder.totalBooks;
        this.books = builder.books;
    }

    public static BookSearchResult.Builder newBuilder() {
        return new Builder();
    }

    public Integer getTotalBooks() {
        return totalBooks;
    }

    public List<BookResponse> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BookSearchResult that = (BookSearchResult) o;
        return Objects.equals(totalBooks, that.totalBooks) &&
                Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalBooks, books);
    }

    @Override
    public String toString() {
        return "BookSearchResult{" +
                "totalBooks=" + totalBooks +
                ", books=" + books +
                '}';
    }

    public static final class Builder {

        private Integer totalBooks;
        private List<BookResponse> books;

        private Builder() {
        }

        public Builder totalBooks(Integer totalBooks) {
            this.totalBooks = totalBooks;
            return this;
        }

        public Builder books(List<BookResponse> books) {
            this.books = books;
            return this;
        }

        public BookSearchResult build() {
            return new BookSearchResult(this);
        }
    }
}
