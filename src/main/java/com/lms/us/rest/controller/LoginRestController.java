package com.lms.us.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.svc.common.model.AuthenticatedUser;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.LoginJson;
import com.lms.us.rest.service.LoginService;
import com.lms.us.rest.service.UserRegistrationService;

@RestController
public class LoginRestController {
	private LoginService loginService;
	private UserRegistrationService userService;

	public LoginRestController(LoginService loginService, UserRegistrationService userService) {
		this.loginService = loginService;
		this.userService = userService;
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginJson loginJson) {
		AuthenticatedUser loginResponse = loginService.doLogin(loginJson);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
	
	@PostMapping(value = "/logindetails", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> loginAndGetData(@RequestBody LoginJson loginJson) {
		AuthenticatedUser loginResponse = loginService.doLogin(loginJson);
		String userName = loginResponse.getUserName();

		UserData userDetails = userService.getByUserName(userName);
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
}
