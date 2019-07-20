package com.lms.us.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserRegistrationData;
import com.lms.us.rest.model.json.UserRegistrationJson;
import com.lms.us.rest.repository.UserRegistrationRepository;
import com.lms.us.rest.transformer.UserTransformer;

@Service
public class UserRegistrationService {
	private UserRegistrationRepository userRegistrationRepository;
	private LoginService loginService;

	public UserRegistrationService(UserRegistrationRepository userRegistrationRepository, LoginService loginService) {
		this.userRegistrationRepository = userRegistrationRepository;
		this.loginService = loginService;
	}

	public List<UserRegistrationJson> getAllUsers() {
		List<UserRegistrationJson> userRegistrationJsons = new ArrayList<>();

		userRegistrationRepository.findAll()
				.forEach(user -> userRegistrationJsons.add(UserTransformer.userRegistrationToUserJson(user)));

		return userRegistrationJsons;
	}

	public UserRegistrationJson getUserById(String userId) {
		return UserTransformer.userRegistrationToUserJson(searchUserById(userId));
	}
	
	public UserRegistrationJson getByUserName(String userName) {
		return UserTransformer.userRegistrationToUserJson(searchByUsername(userName));
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserRegistrationJson addUser(UserRegistrationJson user) {
		user.setUserId("U" + System.currentTimeMillis());
		UserRegistrationData userRegData = UserTransformer.userJsonToUserRegistration(user);
		userRegData.setRegistrationDate(ApplicationCommonConstants.getCurrentDateAsString());
		UserTransformer.setUserStatus(userRegData, ApplicationCommonConstants.STATUS_CODE_ACTIVE);
		
		LoginData loginData = UserTransformer.userJsonToUserLoginData(user);
		loginData.setStatus(userRegData.getStatus());
		loginService.updateLoginInfo(loginData);

		userRegData.setLoginData(loginData);
		UserRegistrationData saved = userRegistrationRepository.save(userRegData);

		return UserTransformer.userRegistrationToUserJson(saved);
	}

	public Object deleteUser(String userId) {
		UserRegistrationData user = searchUserById(userId);
		
		UserTransformer.setUserStatus(user, ApplicationCommonConstants.STATUS_CODE_DELETED);
		
		UserRegistrationData savedUser = userRegistrationRepository.save(user);
		
		return savedUser.getStatus();
	}

	public UserRegistrationJson updateUser(String userId, UserRegistrationJson user) {
		return null;
	}

	private UserRegistrationData searchUserById(String userId) {
		Optional<UserRegistrationData> searchResult = userRegistrationRepository.findById(userId);
		if (searchResult.isPresent()) {
			UserRegistrationData userRegistrationData = searchResult.get();
			return userRegistrationData;
		}
		throw new NoSuchUserException();
	}
	private UserRegistrationData searchByUsername(String userName) {
		Optional<UserRegistrationData> searchResult = userRegistrationRepository.findByUserName(userName);
		if (searchResult.isPresent()) {
			UserRegistrationData userRegistrationData = searchResult.get();
			return userRegistrationData;
		}
		throw new NoSuchUserException();
	}
}
