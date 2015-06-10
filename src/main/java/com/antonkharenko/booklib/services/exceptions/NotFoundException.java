package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class NotFoundException extends ServiceException {

	private final static int CODE = 404;

	public NotFoundException(String statusMessage) {
		super(CODE, statusMessage);
	}

	public NotFoundException(Throwable cause) {
		super(CODE, cause);
	}

	public NotFoundException(String statusMessage, Throwable cause) {
		super(CODE, statusMessage, cause);
	}

}