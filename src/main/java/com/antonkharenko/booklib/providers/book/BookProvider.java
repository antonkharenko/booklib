package com.antonkharenko.booklib.providers.book;

import com.google.common.base.Optional;

import com.antonkharenko.booklib.api.BookSearchResult;
import com.antonkharenko.booklib.domain.Book;

/**
 * @author Anton Kharenko
 */
public interface BookProvider {

    BookSearchResult search(String query);

    Optional<Book> getBook(String bookId);

}
