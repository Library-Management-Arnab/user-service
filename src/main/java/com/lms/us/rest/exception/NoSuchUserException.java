package com.lms.us.rest.exception;

import org.springframework.http.HttpStatus;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.ApplicationError;

public class NoSuchUserException extends ApplicationError {

	private static final long serialVersionUID = 8028858099788854844L;

	private final String message;
	private final int errorCode;
	private final HttpStatus httpStatus;
	private final String errorTime;
	
	public NoSuchUserException() {
		super(ApplicationCommonConstants.NO_SUCH_USER_ERROR_MESSAGE);
		this.message = super.getMessage();
		this.errorCode = ApplicationCommonConstants.NO_SUCH_USER_ERROR_CODE;
		this.httpStatus = HttpStatus.NOT_FOUND;
		this.errorTime = ApplicationCommonConstants.getCurrentDateAsString();
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String getErrorTime() {
		return errorTime;
	}
	
	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
