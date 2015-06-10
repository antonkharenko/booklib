package com.antonkharenko.booklib.providers.book.google;

import com.antonkharenko.booklib.api.*;
import com.antonkharenko.booklib.domain.Book;
import com.antonkharenko.booklib.providers.book.BookProvider;
import com.google.common.base.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Anton Kharenko
 */
@Component
public class GoogleBookProvider implements BookProvider {

	private final static String SEARCH_URL = "https://www.googleapis.com/books/v1/volumes?q={query}&printType=books";
	private final static String GET_BOOK_URL = "https://www.googleapis.com/books/v1/volumes/{volumeId}";

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public BookSearchResult search(String query) {
		VolumeList volumeList = restTemplate.getForObject(SEARCH_URL, VolumeList.class, query);

		return toBookSearchResult(volumeList);
	}

	@Override
	public Optional<Book> getBook(String bookId) {
		Volume volume = restTemplate.getForObject(GET_BOOK_URL, Volume.class, bookId);

		if (volume != null && volume.getVolumeInfo() != null) {
			return Optional.of(toBook(volume.getVolumeInfo()));
		} else {
			return Optional.absent();
		}
	}

	private BookSearchResult toBookSearchResult(VolumeList volumeList) {
		// Prepare books
		List<BookResponse> books = volumeList.getItems().stream()
				.map(this::toBookResponse)
				.collect(toList());

		// Prepare search result
		return BookSearchResult.newBuilder()
				.totalBooks(volumeList.getTotalItems())
				.books(books)
				.build();
	}

	private Book toBook(VolumeInfo volumeInfo) {
		Book book = new Book();
		book.setAuthors(volumeInfo.getAuthors());
		book.setCategories(volumeInfo.getCategories());
		book.setCoverImages(volumeInfo.getImageLinks());
		book.setDescription(volumeInfo.getDescription());
		book.setIndustryIdentifiers(volumeInfo.getIndustryIdentifiers());
		book.setLanguage(volumeInfo.getLanguage());
		book.setMainCategory(volumeInfo.getMainCategory());
		book.setPageCount(volumeInfo.getPageCount());
		book.setPublishedDate(volumeInfo.getPublishedDate());
		book.setPublisher(volumeInfo.getPublisher());
		book.setSubtitle(volumeInfo.getSubtitle());
		book.setTitle(volumeInfo.getTitle());
		return book;
	}

	private BookResponse toBookResponse(Volume volume) {
		BookResponse.Builder bookBuilder = BookResponse.newBuilder()
				.id(volume.getId());

		VolumeInfo volumeInfo = volume.getVolumeInfo();
		if (volumeInfo != null) {
			bookBuilder
					.title(volumeInfo.getTitle())
					.subtitle(volumeInfo.getSubtitle())
					.authors(volumeInfo.getAuthors())
					.description(volumeInfo.getDescription())
					.publishedDate(volumeInfo.getPublishedDate())
					.pageCount(volumeInfo.getPageCount())
					.coverImages(volumeInfo.getImageLinks());
		}

		return bookBuilder.build();
	}

}
