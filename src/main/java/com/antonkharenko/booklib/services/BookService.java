package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.api.SaveBookRequest;
import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.api.BookResponse;
import com.antonkharenko.booklib.api.BookSearchResult;

import java.util.List;

/**
 * @author Anton Kharenko
 */
public interface BookService {

	BookSearchResult searchBooks(String query, User user);

	BookResponse saveBook(SaveBookRequest request, User user);

	void deleteBook(String bookId, User user);

	BookResponse getBook(String bookId, User user);

	List<BookResponse> getBookList(User user);

}
