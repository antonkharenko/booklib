package com.antonkharenko.booklib.resources;

import com.antonkharenko.booklib.api.SaveBookRequest;
import com.antonkharenko.booklib.domain.User;
import com.antonkharenko.booklib.services.BookService;
import io.dropwizard.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Anton Kharenko
 */
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class BookResource {

	private final BookService bookService;
	private final ExceptionHandler exceptionHandler;

	@Autowired
	public BookResource(BookService bookService, ExceptionHandler exceptionHandler) {
		this.bookService = bookService;
		this.exceptionHandler = exceptionHandler;
	}

	@GET
	@Path("/search")
	public Object searchBooks(
			@QueryParam("q") String query,
			@Auth User user) {
		try {
			return bookService.searchBooks(query, user);
		} catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}

	@POST
	public Object saveBook(
			@Valid SaveBookRequest request,
			@Auth User user) {
		try {
			return bookService.saveBook(request, user);
		} catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}

	@DELETE
	@Path("/{book_id}")
	public Object deleteBook(
			@PathParam("book_id") String bookId,
			@Auth User user) {
		try {
			bookService.deleteBook(bookId, user);
			return Response.noContent().build();
		} catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}

	@GET
	public Object getBookList(@Auth User user) {
		try {
			return bookService.getBookList(user);
		} catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}

	@GET
	@Path("/{book_id}")
	public Object getBook(
			@PathParam("book_id") String bookId,
			@Auth User user) {
		try {
			return bookService.getBook(bookId, user);
		} catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}

}
