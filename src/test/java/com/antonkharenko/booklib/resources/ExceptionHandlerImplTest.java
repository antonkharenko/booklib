package com.antonkharenko.booklib.resources;

import com.antonkharenko.booklib.api.ErrorResponse;
import com.antonkharenko.booklib.services.exceptions.*;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

/**
 * @author Anton Kharenko
 */
public class ExceptionHandlerImplTest {

	private ExceptionHandlerImpl exceptionHandler = new ExceptionHandlerImpl();

	@Test
	public void testHandleConflictException() {
		// Given
		int expectedStatusCode = Response.Status.CONFLICT.getStatusCode();
		String expectedMessage = "Test conflict error.";
		ServiceException exception = new ConflictException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testHandleBadRequestException() {
		// Given
		int expectedStatusCode = Response.Status.BAD_REQUEST.getStatusCode();
		String expectedMessage = "Test bad request error.";
		ServiceException exception = new BadRequestException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testForbiddenException() {
		// Given
		int expectedStatusCode = Response.Status.FORBIDDEN.getStatusCode();
		String expectedMessage = "Test forbidden error.";
		ServiceException exception = new ForbiddenException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testInternalServerErrorException() {
		// Given
		int expectedStatusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		String expectedMessage = "Test internal server error.";
		ServiceException exception = new InternalServerErrorException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testNotFoundException() {
		// Given
		int expectedStatusCode = Response.Status.NOT_FOUND.getStatusCode();
		String expectedMessage = "Test not found error.";
		ServiceException exception = new NotFoundException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testServiceUnavailableException() {
		// Given
		int expectedStatusCode = Response.Status.SERVICE_UNAVAILABLE.getStatusCode();
		String expectedMessage = "Test service unavailable error.";
		ServiceException exception = new ServiceUnavailableException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testUnauthorizedException() {
		// Given
		int expectedStatusCode = Response.Status.UNAUTHORIZED.getStatusCode();
		String expectedMessage = "Test unauthorized error.";
		ServiceException exception = new UnauthorizedException(expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testHandleCustomServiceException() {
		// Given
		int expectedStatusCode = 403;
		String expectedMessage = "Test error.";
		ServiceException exception = new ServiceException(expectedStatusCode, expectedMessage);

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

	@Test
	public void testHandleUnknownException() {
		// Given
		int expectedStatusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		String expectedMessage = ExceptionHandlerImpl.INTERNAL_SERVER_ERROR_MESSAGE;
		NullPointerException exception = new NullPointerException("Suddenly get NPE");

		// When
		Response errorResponse = exceptionHandler.handleException(exception);

		// Then
		Assert.assertEquals(expectedStatusCode, errorResponse.getStatus());
		Assert.assertTrue(errorResponse.getEntity() instanceof ErrorResponse);
		Assert.assertEquals(expectedMessage, ((ErrorResponse) errorResponse.getEntity()).getMessage());
	}

}
