package com.antonkharenko.booklib.integration;

import com.antonkharenko.booklib.api.BookResponse;
import com.antonkharenko.booklib.api.BookSearchResult;
import com.antonkharenko.booklib.api.UserResponse;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Anton Kharenko
 */
public class BookServiceIT extends AbstractServiceIT {

    private static UserResponse user;

    @BeforeClass
    public static void initData() {
        user = registerUser();
    }

    @Test
    public void testBookSearch() {
        // Given
        final String query = "Effective Java";

        // When
        BookSearchResult bookSearchResult = request("/api/books/search", "GET", null, BookSearchResult.class, user.getApiKey(), "q", query);

        // Then
        assertNotNull(bookSearchResult);
        assertNotNull(bookSearchResult.getBooks());
        assertTrue(bookSearchResult.getTotalBooks() > 0);
        int resultsSize = bookSearchResult.getBooks().size();
        assertTrue(resultsSize > 0);
        for (BookResponse book : bookSearchResult.getBooks()) {
            assertNotNull(book);
            assertNotNull(book.getId());
            assertNotNull(book.getTitle());
        }
    }

}
