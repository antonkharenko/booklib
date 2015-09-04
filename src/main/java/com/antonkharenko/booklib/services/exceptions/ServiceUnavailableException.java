package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class ServiceUnavailableException extends ServiceException {

    private final static int CODE = 503;

    public ServiceUnavailableException(String statusMessage) {
        super(CODE, statusMessage);
    }

    public ServiceUnavailableException(Throwable cause) {
        super(CODE, cause);
    }

    public ServiceUnavailableException(String statusMessage, Throwable cause) {
        super(CODE, statusMessage, cause);
    }

}
