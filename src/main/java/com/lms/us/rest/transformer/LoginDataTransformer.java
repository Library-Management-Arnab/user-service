package com.lms.us.rest.transformer;

import com.lms.svc.common.model.LoginResponse;
import com.lms.us.rest.model.db.LoginData;

public final class LoginDataTransformer {
	private LoginDataTransformer() {}
	
	public static LoginResponse fromLoginData(LoginData loginData) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUserName(loginData.getUserName());
		loginResponse.setUserRight(loginData.getUserRight().getUserRightCode());
		loginResponse.setUserStatus(loginData.getStatus().getStatusCode());
		
		return loginResponse;
	}
}
