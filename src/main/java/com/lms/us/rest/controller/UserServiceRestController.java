package com.lms.us.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.us.rest.model.json.UserJson;
import com.lms.us.rest.service.UserRegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
@Api(value = "User Service Rest Controller")
public class UserServiceRestController {
	private UserRegistrationService userRegistrationService;
	
	@ApiOperation(value = "Display all users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "Users list successfully fetched"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "User not authorized to perform this operation"),
			@ApiResponse(code = 500, message = "Generic error") })
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(userRegistrationService.getAllUsers(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "User registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 201, message = "User successfully registered"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "User not authorized to perform this operation"),
			@ApiResponse(code = 409, message = "Duplicate user."),
			@ApiResponse(code = 500, message = "Generic error") })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> register(@RequestBody UserJson userJson) {
		return new ResponseEntity<>(userRegistrationService.register(userJson), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get user by UserID", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved corresponding user"),
			@ApiResponse(code = 403, message = "User not authorized to perform this operation"),
			@ApiResponse(code = 404, message = "No user was found based on the selected criteria"),
			@ApiResponse(code = 500, message = "Generic error") })
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId) {
		return new ResponseEntity<>(userRegistrationService.getUserById(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an existing user", response = ResponseEntity.class, httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 204, message = "User deleted successfully"),
			@ApiResponse(code = 403, message = "User not authorized to perform this operation"),
			@ApiResponse(code = 404, message = "No user was found based on the selected criteria"),
			@ApiResponse(code = 500, message = "Generic error") })
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId) {
		userRegistrationService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Update user by self", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class, httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully updated user"),
			@ApiResponse(code = 403, message = "User not authorized to perform this operation"),
			@ApiResponse(code = 404, message = "No user was found based on the selected criteria"),
			@ApiResponse(code = 500, message = "Generic error") })
	@PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUser(@PathVariable("userId") String userId,
			@RequestBody UserJson userJson) {
		return new ResponseEntity<>(userRegistrationService.updateUserAsSelf(userId, userJson), HttpStatus.OK);
	}

}
