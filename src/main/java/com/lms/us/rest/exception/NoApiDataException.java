package com.lms.us.rest.exception;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.ApplicationError;
import org.springframework.http.HttpStatus;

public class NoApiDataException extends ApplicationError {

	private static final long serialVersionUID = 8028858099788854844L;

	private final String message;
	private final int errorCode;
	private final HttpStatus httpStatus;

	public NoApiDataException(String userName) {
		super(String.format("No API data present for user [%s]. Login failed!!", userName));
		this.message = super.getMessage();
		this.errorCode = ApplicationCommonConstants.NO_SUCH_USER_ERROR_CODE;
		this.httpStatus = HttpStatus.NOT_FOUND;
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
