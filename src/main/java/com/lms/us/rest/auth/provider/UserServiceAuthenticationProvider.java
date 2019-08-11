package com.lms.us.rest.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.lms.svc.common.model.AuthenticatedUser;
import com.lms.us.rest.auth.model.UserAuthenticationData;
import com.lms.us.rest.model.json.LoginJson;
import com.lms.us.rest.service.LoginService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class UserServiceAuthenticationProvider implements AuthenticationProvider {

	private LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoginJson loginJson = new LoginJson();
		loginJson.setUserName(authentication.getName());
		loginJson.setPassword(authentication.getCredentials().toString());
		AuthenticatedUser authUser = loginService.doLogin(loginJson);

		return new UserAuthenticationData(authUser);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
