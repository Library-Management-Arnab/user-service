package com.lms.us.rest.transformer;


import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserRegistrationData;
import com.lms.us.rest.model.db.UserStatus;
import com.lms.us.rest.model.json.UserRegistrationJson;

public class UserTransformer {

	public static UserRegistrationData userJsonToUserRegistration(UserRegistrationJson userJson) {
		UserRegistrationData userRegistrationData = new UserRegistrationData();

		userRegistrationData.setAddress1(userJson.getAddress1());
		userRegistrationData.setAddress2(userJson.getAddress2());
		userRegistrationData.setContactNo(userJson.getContactNo());
		userRegistrationData.setDisplayName(userJson.getDisplayName());
		userRegistrationData.setEmail(userJson.getEmail());
		userRegistrationData.setFirstName(userJson.getFirstName());
		userRegistrationData.setLastName(userJson.getLastName());
		userRegistrationData.setPin(userJson.getPin());
		userRegistrationData.setUserName(userJson.getUserName());
		userRegistrationData.setUserId(userJson.getUserId());
		userRegistrationData.setUserRight(userJson.getUserRight());
		userRegistrationData.setStatus(userJson.getStatus());
		return userRegistrationData;
	}

	public static UserRegistrationJson userRegistrationToUserJson(UserRegistrationData userRegistrationData) {
		UserRegistrationJson userJson = new UserRegistrationJson();
		userJson.setAddress1(userRegistrationData.getAddress1());
		userJson.setAddress2(userRegistrationData.getAddress2());
		userJson.setContactNo(userRegistrationData.getContactNo());
		userJson.setDisplayName(userRegistrationData.getDisplayName());
		userJson.setEmail(userRegistrationData.getEmail());
		userJson.setFirstName(userRegistrationData.getFirstName());
		userJson.setLastName(userRegistrationData.getLastName());
		userJson.setPin(userRegistrationData.getPin());
		userJson.setUserName(userRegistrationData.getUserName());
		userJson.setUserId(userRegistrationData.getUserId());
		userJson.setStatus(userRegistrationData.getStatus());
//		String accessTypeDBCode = userRegistrationData.getUserAccessType();
		
//		UserRoles userRoles = UserRoles.getByDBCode(accessTypeDBCode);
		
//		UserAccessType userAccessType = new UserAccessType();
//		userAccessType.setAccessType(userRoles.getAccessType());
//		userAccessType.setDescription(userRoles.getDescription());
//		userJson.setUserAccessType(userAccessType);
		
		userJson.setUserRight(userRegistrationData.getUserRight());
		return userJson;
	}
	public static LoginData userJsonToUserLoginData(UserRegistrationJson userJson) {
		LoginData loginData = new LoginData();
		loginData.setUserName(userJson.getUserName());
		loginData.setPassword(userJson.getPassword());
		loginData.setUserId(userJson.getUserId());
		loginData.setStatus(userJson.getStatus());
		loginData.setUserRight(userJson.getUserRight());
		return loginData;
	}
	public static void setUserStatus(UserRegistrationData user, String statusCode) {
		setUserStatus(user, statusCode, null);
	}
	public static void setUserStatus(UserRegistrationData user, String statusCode, String statusDescription) {
		UserStatus status = new UserStatus();
		status.setStatusCode(statusCode);
		status.setStatusDescription(statusDescription);
		user.setStatus(status );
	}
}
