package com.lms.us.rest.controller;

import com.lms.svc.common.model.AuthenticatedUser;
import com.lms.us.rest.model.json.LoginJson;
import com.lms.us.rest.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(value = "User Login Rest Controller")
public class LoginRestController {
	private LoginService loginService;
	
	@ApiOperation(value = "User Login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "User successfully authenticated"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 500, message = "Generic error") })
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginJson loginJson) {
		AuthenticatedUser loginResponse = loginService.doLogin(loginJson);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
}
