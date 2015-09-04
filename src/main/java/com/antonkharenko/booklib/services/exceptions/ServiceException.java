package com.antonkharenko.booklib.services.exceptions;

/**
 * @author Anton Kharenko
 */
public class ServiceException extends RuntimeException {

    private final int statusCode;

    public ServiceException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
    }

    public ServiceException(int statusCode, String statusMessage, Throwable cause) {
        super(statusMessage, cause);
        this.statusCode = statusCode;
    }

    public ServiceException(int statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
