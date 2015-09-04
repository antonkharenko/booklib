package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class ConflictException extends ServiceException {

    private final static int CODE = 409;

    public ConflictException(String statusMessage) {
        super(CODE, statusMessage);
    }

    public ConflictException(Throwable cause) {
        super(CODE, cause);
    }

    public ConflictException(String statusMessage, Throwable cause) {
        super(CODE, statusMessage, cause);
    }

}
