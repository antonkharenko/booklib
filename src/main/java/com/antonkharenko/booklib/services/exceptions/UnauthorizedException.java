package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class UnauthorizedException extends ServiceException {

	private final static int CODE = 401;

	public UnauthorizedException(String statusMessage) {
		super(CODE, statusMessage);
	}

	public UnauthorizedException(Throwable cause) {
		super(CODE, cause);
	}

	public UnauthorizedException(String statusMessage, Throwable cause) {
		super(CODE, statusMessage, cause);
	}

}