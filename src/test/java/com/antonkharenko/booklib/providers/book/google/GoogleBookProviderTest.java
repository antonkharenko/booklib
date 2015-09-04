package com.antonkharenko.booklib.providers.book.google;

import com.antonkharenko.booklib.annotation.type.IntegrationTest;
import com.antonkharenko.booklib.api.BookResponse;
import com.antonkharenko.booklib.api.BookSearchResult;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Anton Kharenko
 */
@Category(IntegrationTest.class)
public class GoogleBookProviderTest {

    private static final Logger logger = LoggerFactory.getLogger(GoogleBookProviderTest.class);

    private GoogleBookProvider bookProvider = new GoogleBookProvider();

    @Test
    public void testSearch() throws Exception {
        // Given
        final String query = "Java effective";

        // When
        BookSearchResult bookSearchResult = bookProvider.search(query);

        // Then
        assertNotNull(bookSearchResult);
        assertNotNull(bookSearchResult.getBooks());
        int resultsSize = bookSearchResult.getBooks().size();
        assertTrue(resultsSize > 0);
        logger.info("For query '{}' found {} results out of total {} matches:", query, resultsSize, bookSearchResult.getTotalBooks());
        for (int i = 0; i < Math.min(10, resultsSize); i++) {
            BookResponse book = bookSearchResult.getBooks().get(i);
            logger.info("{}: {}", i, book);
            assertNotNull(book);
            assertNotNull(book.getId());
            assertNotNull(book.getTitle());
            assertNotNull(book.getAuthors());
            assertTrue(book.getAuthors().size() > 0);
        }
    }

}
