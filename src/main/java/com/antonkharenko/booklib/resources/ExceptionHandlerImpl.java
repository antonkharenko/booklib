package com.antonkharenko.booklib.resources;

import com.antonkharenko.booklib.services.exceptions.ServiceException;
import com.antonkharenko.booklib.api.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * @author Anton Kharenko
 */
@Component
public class ExceptionHandlerImpl implements ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerImpl.class);

	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error.";

	@Override
	public Response handleException(Exception exception) {
		logger.error("Error on handling request: ", exception);

		// Define error response parameters
		int errorStatusCode;
		ErrorResponse errorResponse;
		if (exception instanceof ServiceException) {
			ServiceException serviceException = (ServiceException) exception;
			errorResponse = ErrorResponse.newBuilder()
					.message(serviceException.getMessage())
					.build();
			errorStatusCode = serviceException.getStatusCode();
		} else {
			errorStatusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
			errorResponse = ErrorResponse.newBuilder()
					.message(INTERNAL_SERVER_ERROR_MESSAGE)
					.build();
		}
		// Build error response
		return Response
				.status(errorStatusCode)
				.entity(errorResponse)
				.build();
	}
}
