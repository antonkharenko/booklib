package com.antonkharenko.booklib.providers.book;

import com.antonkharenko.booklib.api.BookSearchResult;
import com.antonkharenko.booklib.domain.Book;
import com.google.common.base.Optional;

/**
 * @author Anton Kharenko
 */
public interface BookProvider {

	BookSearchResult search(String query);

	Optional<Book> getBook(String bookId);

}
