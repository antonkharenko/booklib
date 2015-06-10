package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class RequestEntityTooLarge extends ServiceException {

    private final static int CODE = 413;

    public RequestEntityTooLarge(String statusMessage) {
        super(CODE, statusMessage);
    }

    public RequestEntityTooLarge(Throwable cause) {
        super(CODE, cause);
    }

    public RequestEntityTooLarge(String statusMessage, Throwable cause) {
        super(CODE, statusMessage, cause);
    }

}
