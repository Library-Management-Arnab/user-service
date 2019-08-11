package com.lms.us.rest.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.us.rest.exception.DuplicateUserException;
import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserJson;
import com.lms.us.rest.repository.UserRegistrationRepository;
import com.lms.us.rest.transformer.UserDataTransformer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRegistrationService {
	private UserRegistrationRepository userRegistrationRepository;
	private LoginService loginService;
	private UserDataTransformer userDataTransformer;


	public List<UserJson> getAllUsers() {
		List<UserData> allUsers = userRegistrationRepository.findAll();
		return userDataTransformer.userDataListToUserJsonList(allUsers);
	}

	public UserJson getUserById(String userId) {
		UserData foundUser = searchAndValidateByUserId(userId);
		return userDataTransformer.userDataToUserJson(foundUser);
	}
	
	public UserJson getByUserName(String userName) {
		UserData foundUser = searchAndValidateByUserName(userName);
		return userDataTransformer.userDataToUserJson(foundUser);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserJson register(UserJson userJson) {
		validateDuplicateUser(userJson.getUserName());
		String currentDate = ApplicationCommonConstants.getCurrentDateAsString();
		userJson.setRegistrationDate(currentDate);
		userJson.setLastUpdateDate(currentDate);
		userJson.setStatus(ApplicationCommonConstants.USER_STATUS_ACTIVE);

		// TODO - to be separated for admin and user later
		userJson.setRights(Arrays.asList(ApplicationCommonConstants.USER_RIGHT_BASIC));
		
		UserData registrationData = userDataTransformer.userJsonToUserData(userJson);

		LoginData loginData = extractLoginData(registrationData);
		loginData.setStatus(registrationData.getStatus());
		
		loginService.updateLoginInfo(loginData);
		registrationData.setLoginData(loginData);
		
		UserData registeredUser = userRegistrationRepository.save(registrationData);
		return userDataTransformer.userDataToUserJson(registeredUser);
	}

	public void deleteUser(String userId) {
		UserData user = searchUserById(userId);
		user.setStatus(userDataTransformer.getUserStatusFromDescription(ApplicationCommonConstants.USER_STATUS_CODE_DELETED));
		user.setLastUpdateDate(ApplicationCommonConstants.getCurrentDateAsString());
		userRegistrationRepository.save(user);
	}

	public UserJson updateUserAsSelf(String userId, UserJson userJson) {
		UserData existing = searchUserById(userId);

		UserData input = userDataTransformer.userJsonToUserData(userJson);
		
		//Restoring the existing userId
		input.setUserId(existing.getUserId());
		
		// Basic user cannot update its own status
		input.setStatus(existing.getStatus());

		// Basic user cannot update its own rights
		input.setUserRights(existing.getUserRights());

		// Registration date is unchangeable
		input.setRegistrationDate(existing.getRegistrationDate());

		input.setLastUpdateDate(ApplicationCommonConstants.getCurrentDateAsString());

		UserData updatedUser = userRegistrationRepository.save(input);

		return userDataTransformer.userDataToUserJson(updatedUser);
	}
	
	// TODO - To be added later
	public UserJson updateUserAsAdmin(String userId, UserJson userJson) {
		UserData existing = searchUserById(userId);

		UserData input = userDataTransformer.userJsonToUserData(userJson);

		// Registration date is unchangeable
		input.setRegistrationDate(existing.getRegistrationDate());
		input.setLastUpdateDate(ApplicationCommonConstants.getCurrentDateAsString());

		UserData updatedUser = userRegistrationRepository.save(input);
		return userDataTransformer.userDataToUserJson(updatedUser);
	}

	private UserData searchUserById(String userId) {
		Optional<UserData> searchResult = userRegistrationRepository.findById(userId);
		return searchResult.isPresent() ? searchResult.get() : null;
	}
	private UserData searchAndValidateByUserId(String userId) {
		UserData foundUser = searchUserById(userId);
		if (foundUser != null) {
			return foundUser;
		}
		throw new NoSuchUserException();
	}
	private UserData searchAndValidateByUserName(String userName) {
		UserData foundUser = searchByUsername(userName);
		if (foundUser != null) {
			return foundUser;
		}
		throw new NoSuchUserException();
	}
	private UserData searchByUsername(String userName) {
		Optional<UserData> searchResult = userRegistrationRepository.findByUserName(userName);
		return searchResult.isPresent() ? searchResult.get() : null;
	}
	
	private void validateDuplicateUser(String userName) {
		UserData found = searchByUsername(userName);
		if(found != null && found.getUserId() != null) {
			throw new DuplicateUserException(userName);
		}
	}
	
	private LoginData extractLoginData(UserData userData) {
		LoginData loginData = new LoginData();
		loginData.setUserName(userData.getUserName());
		loginData.setPassword(userData.getPassword());
		loginData.setUserId(userData.getUserId());
		loginData.setStatus(userData.getStatus());
		return loginData;
	}
}
