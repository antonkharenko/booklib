package com.antonkharenko.booklib.services;

import com.antonkharenko.booklib.api.SaveBookRequest;
import com.antonkharenko.booklib.domain.Book;
import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.api.BookResponse;
import com.antonkharenko.booklib.api.BookSearchResult;
import com.antonkharenko.booklib.providers.book.BookProvider;
import com.antonkharenko.booklib.repository.BookRepository;
import com.antonkharenko.booklib.services.exceptions.BadRequestException;
import com.antonkharenko.booklib.services.exceptions.NotFoundException;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Anton Kharenko
 */
@Component
public class BookServiceImpl implements BookService {

	private final BookProvider bookProvider;
	private final BookRepository bookRepository;

	@Autowired
	public BookServiceImpl(BookProvider bookProvider, BookRepository bookRepository) {
		this.bookProvider = bookProvider;
		this.bookRepository = bookRepository;
	}

	@Override
	public BookSearchResult searchBooks(String query, User user) {
		if (Strings.isNullOrEmpty(query)) {
			throw new BadRequestException("Missed search query.");
		}
		return bookProvider.search(query);
	}

	@Override
	public BookResponse saveBook(SaveBookRequest request, User user) {
		Optional<Book> optionalBook = bookProvider.getBook(request.getSearchBookId());
		if (!optionalBook.isPresent())
			throw new NotFoundException("No such book found.");

		Book book = optionalBook.get();
		book.setUserId(user.getId());
		Book savedBook = bookRepository.save(book);

		return toBookResponse(savedBook);
	}

	@Override
	public void deleteBook(String bookId, User user) {
		Book book = getBookEntity(bookId, user.getId());
		bookRepository.delete(book);
	}

	@Override
	public BookResponse getBook(String bookId, User user) {
		Book book = getBookEntity(bookId, user.getId());
		return toBookResponse(book);
	}

	@Override
	public List<BookResponse> getBookList(User user) {
		List<Book> bookList = bookRepository.findByUserId(user.getId());
		return bookList.stream()
				.map(this::toBookResponse)
				.collect(toList());
	}

	private Book getBookEntity(String bookId, String userId) {
		Optional<Book> optionalBook = bookRepository.findByIdAndUserId(bookId, userId);
		if (!optionalBook.isPresent())
			throw new NotFoundException("No such book stored for given user.");

		return optionalBook.get();
	}

	private BookResponse toBookResponse(Book book) {
		return BookResponse.newBuilder()
				.id(book.getId())
				.authors(book.getAuthors())
				.categories(book.getCategories())
				.coverImages(book.getCoverImages())
				.description(book.getDescription())
				.industryIdentifiers(book.getIndustryIdentifiers())
				.language(book.getLanguage())
				.mainCategory(book.getMainCategory())
				.pageCount(book.getPageCount())
				.publishedDate(book.getPublishedDate())
				.publisher(book.getPublisher())
				.subtitle(book.getSubtitle())
				.title(book.getTitle())
				.build();
	}
}
