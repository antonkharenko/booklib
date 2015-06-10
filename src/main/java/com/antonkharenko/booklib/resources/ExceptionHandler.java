package com.antonkharenko.booklib.resources;

import javax.ws.rs.core.Response;

/**
 * @author Anton Kharenko
 */
public interface ExceptionHandler {

	Response handleException(Exception exception);

}
