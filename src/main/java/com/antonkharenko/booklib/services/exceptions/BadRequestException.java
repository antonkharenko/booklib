package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class BadRequestException extends ServiceException {

    private final static int CODE = 400;

    public BadRequestException(String statusMessage) {
        super(CODE, statusMessage);
    }

    public BadRequestException(Throwable cause) {
        super(CODE, cause);
    }

    public BadRequestException(String statusMessage, Throwable cause) {
        super(CODE, statusMessage, cause);
    }

}
