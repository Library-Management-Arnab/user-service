package com.lms.us.rest.service;

import com.lms.svc.common.crypto.CryptographyUtil;
import com.lms.svc.common.exception.InvalidCredentialsException;
import com.lms.svc.common.model.AuthenticatedUser;
import com.lms.us.rest.exception.NoApiDataException;
import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.auth.UserApiData;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.LoginDataJson;
import com.lms.us.rest.model.json.LoginJson;
import com.lms.us.rest.repository.LoginRepository;
import com.lms.us.rest.repository.UserApiDataRepository;
import com.lms.us.rest.transformer.LoginDataTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService  {
	private LoginRepository loginRepository;
	private UserApiDataRepository userApiDataRepository;
	private LoginDataTransformer loginDataTransformer;

	public AuthenticatedUser doLogin(LoginJson loginJson) {
		Optional<LoginData> searchResult = loginRepository.findById(loginJson.getUserName());

		if (searchResult.isPresent()) {
			LoginData saved = searchResult.get();

			String encryptedPassword = CryptographyUtil.encrypt(loginJson.getPassword(), saved.getSecret());

			if (saved.getPassword().equals(encryptedPassword)) {
				saved.setPassword(null);
				saved.setSecret(null);
				
				return fromLoginData(saved);
			}
		}
		throw new InvalidCredentialsException();
	}
    public LoginDataJson getLoginDataJson(String userName) {
	    return loginDataTransformer.toLoginDataJson(getLoginData(userName));
    }
    public LoginData getLoginData(String userName) {
        Optional<LoginData> found = loginRepository.findByUserName(userName);
        if(found .isPresent()) {
            return found.get();
        }
        throw new NoSuchUserException();
    }
	private AuthenticatedUser fromLoginData(LoginData loginData) {
		AuthenticatedUser loginResponse = new AuthenticatedUser();
		loginResponse.setUserName(loginData.getUserName());
		loginResponse.setUserStatus(loginData.getStatus().getStatusCode());
		return loginResponse;
	}
	private UserApiData getApiData(LoginData loginData) {
        UserData userData = new UserData();
        userData.setUserId(loginData.getUserId());

        Optional<UserApiData> foundApiData = userApiDataRepository.findByUserId(loginData.getUserId());

        if(foundApiData.isPresent()) {
            return foundApiData.get();
        } else {
            throw new NoApiDataException(loginData.getUserName());
        }
    }
}
