package com.lms.us.rest.exception;

import org.springframework.http.HttpStatus;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.ApplicationError;

public class DuplicateUserException extends ApplicationError {

	private static final long serialVersionUID = 8028858099788854844L;

	private final String message;
	private final int errorCode;
	private final HttpStatus httpStatus;
	private final String errorTime;
	
	public DuplicateUserException(String userName) {
		super(String.format(ApplicationCommonConstants.DUPLICATE_USER_ERROR_MESSAGE, userName));
		this.message = super.getMessage();
		this.errorCode = ApplicationCommonConstants.DUPLICATE_USER_ERROR_CODE;
		this.httpStatus = HttpStatus.CONFLICT;
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
