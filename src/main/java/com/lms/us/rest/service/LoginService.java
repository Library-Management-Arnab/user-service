package com.lms.us.rest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.svc.common.crypto.CryptographyUtil;
import com.lms.svc.common.exception.InvalidCredentialsException;
import com.lms.svc.common.model.LoginResponse;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.json.LoginJson;
import com.lms.us.rest.repository.LoginRepository;
import com.lms.us.rest.transformer.LoginDataTransformer;

@Service
public class LoginService {
	private LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public LoginResponse doLogin(LoginJson loginJson) {
		Optional<LoginData> searchResult = loginRepository.findById(loginJson.getUserName());

		if (searchResult.isPresent()) {
			LoginData saved = searchResult.get();

			String encryptedPassword = CryptographyUtil.encrypt(loginJson.getPassword(), saved.getSecret());

			if (saved.getPassword().equals(encryptedPassword)) {
				saved.setPassword(null);
				saved.setSecret(null);
				return LoginDataTransformer.fromLoginData(saved);
			}
		}
		throw new InvalidCredentialsException();
	}

	public void updateLoginInfo(LoginData loginData) {
		String secret = CryptographyUtil.generateSecret();

		String encryptedPassword = CryptographyUtil.encrypt(loginData.getPassword(), secret);

		loginData.setSecret(secret);
		loginData.setPassword(encryptedPassword);
	}
}
