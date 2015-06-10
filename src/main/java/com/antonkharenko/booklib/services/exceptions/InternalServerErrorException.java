package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class InternalServerErrorException extends ServiceException {

	private final static int CODE = 500;

	public InternalServerErrorException(String statusMessage) {
		super(CODE, statusMessage);
	}

	public InternalServerErrorException(Throwable cause) {
		super(CODE, cause);
	}

	public InternalServerErrorException(String statusMessage, Throwable cause) {
		super(CODE, statusMessage, cause);
	}

}