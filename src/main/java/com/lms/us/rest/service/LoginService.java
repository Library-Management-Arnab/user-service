package com.lms.us.rest.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.svc.common.crypto.CryptographyUtil;
import com.lms.svc.common.exception.InvalidCredentialsException;
import com.lms.svc.common.model.AuthenticatedUser;
import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.LoginJson;
import com.lms.us.rest.repository.LoginRepository;
import com.lms.us.rest.repository.UserRegistrationRepository;

@Service
public class LoginService implements UserDetailsService {
	private LoginRepository loginRepository;
	private UserRegistrationRepository userRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

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

	public void updateLoginInfo(LoginData loginData) {
		String secret = createSecret();

		String encryptedPassword = CryptographyUtil.encrypt(loginData.getPassword(), secret);

		loginData.setSecret(secret);
		loginData.setPassword(encryptedPassword);
	}
	public String createSecret() {
		return CryptographyUtil.generateSecret();
	}
	private AuthenticatedUser fromLoginData(LoginData loginData) {
		AuthenticatedUser loginResponse = new AuthenticatedUser();
		loginResponse.setUserName(loginData.getUserName());
		loginResponse.setUserStatus(loginData.getStatus().getStatusCode());
		
		return loginResponse;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<UserData> searchResult = userRepository.findByUserName(userName);
		if (searchResult.isPresent()) {
			return new com.lms.us.rest.model.auth.AuthenticatedUser(searchResult.get());
		}
		throw new NoSuchUserException();
	}
}
