package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class ForbiddenException extends ServiceException {

    private final static int CODE = 403;

    public ForbiddenException(String statusMessage) {
        super(CODE, statusMessage);
    }

    public ForbiddenException(Throwable cause) {
        super(CODE, cause);
    }

    public ForbiddenException(String statusMessage, Throwable cause) {
        super(CODE, statusMessage, cause);
    }

}
