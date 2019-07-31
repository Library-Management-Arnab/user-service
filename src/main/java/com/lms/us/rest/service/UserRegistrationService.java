package com.lms.us.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.exception.InvalidFieldValueException;
import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.db.UserRight;
import com.lms.us.rest.model.db.UserStatus;
import com.lms.us.rest.model.json.UserDataJson;
import com.lms.us.rest.repository.UserRegistrationRepository;
import com.lms.us.rest.repository.UserRightRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRegistrationService {
	private UserRegistrationRepository userRegistrationRepository;
	private LoginService loginService;
	private UserRightRepository userRightRepository;


	public List<UserData> getAllUsers() {
		return userRegistrationRepository.findAll();
	}

	public UserData getUserById(String userId) {
		return searchUserById(userId);
	}
	
	public UserData getByUserName(String userName) {
		return searchByUsername(userName);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserData register(UserDataJson userRegDataJson) {
		System.out.println(userRegDataJson.getUserId());
		
		String registrationDate = ApplicationCommonConstants.getCurrentDateAsString();
		userRegData.setRegistrationDate(registrationDate);
		userRegData.setLastUpdateDate(registrationDate);
		setUserStatus(userRegData, ApplicationCommonConstants.USER_STATUS_CODE_ACTIVE);
		
		// TODO - to be separated for admin and user later
		setUserRight(userRegData);

		LoginData loginData = extractLoginData(userRegData);
		loginData.setStatus(userRegData.getStatus());
		
		loginService.updateLoginInfo(loginData);
		userRegData.setLoginData(loginData);
		
		return userRegistrationRepository.save(userRegData);
	}

	public void deleteUser(String userId) {
		UserData user = searchUserById(userId);
		setUserStatus(user, ApplicationCommonConstants.USER_STATUS_CODE_DELETED);
		user.setLastUpdateDate(ApplicationCommonConstants.getCurrentDateAsString());
		userRegistrationRepository.save(user);
	}

	public UserData updateUserAsSelf(String userId, UserData user) {
		UserData existing = searchUserById(userId);
		
		user.setStatus(existing.getStatus());
		user.setUserRight(existing.getUserRight());
		user.setRegistrationDate(existing.getRegistrationDate());
		user.setLastUpdateDate(ApplicationCommonConstants.getCurrentDateAsString());
		
		return userRegistrationRepository.save(user);
	}
	
	// TODO - To be added later
	public UserData updateUserAsAdmin(String userId, UserData user) {
		UserData existing = searchUserById(userId);
		
		user.setStatus(existing.getStatus());
		user.setUserRight(existing.getUserRight());
		
		return userRegistrationRepository.save(user);
	}

	private UserData searchUserById(String userId) {
		Optional<UserData> searchResult = userRegistrationRepository.findById(userId);
		if (searchResult.isPresent()) {
			UserData userRegistrationData = searchResult.get();
			return userRegistrationData;
		}
		throw new NoSuchUserException();
	}
	
	private UserData searchByUsername(String userName) {
		Optional<UserData> searchResult = userRegistrationRepository.findByUserName(userName);
		if (searchResult.isPresent()) {
			UserData userRegistrationData = searchResult.get();
			return userRegistrationData;
		}
		throw new NoSuchUserException();
	}
	
	private void setUserRight(UserData data) {
		Optional<UserRight> searchedRight = userRightRepository.findByRight(data.getUserRight().getRight());
		
		if(searchedRight.isPresent()) {
			data.setUserRight(searchedRight.get());
		} else {
			throw new InvalidFieldValueException("UserRight", data.getUserRight().getRight(), userRightRepository.findAllRights());
		}
	}
	
	private void setUserStatus(UserData user, String statusCode) {
		setUserStatus(user, statusCode, null);
	}
	
	private void setUserStatus(UserData user, String statusCode, String statusDescription) {
		UserStatus status = new UserStatus();
		status.setStatusCode(statusCode);
		status.setStatusDescription(statusDescription);
		user.setStatus(status );
	}
	
	private LoginData extractLoginData(UserData userData) {
		LoginData loginData = new LoginData();
		loginData.setUserName(userData.getUserName());
		loginData.setPassword(userData.getPassword());
		loginData.setUserId(userData.getUserId());
		loginData.setStatus(userData.getStatus());
		loginData.setUserRight(userData.getUserRight());
		return loginData;
	}
}
