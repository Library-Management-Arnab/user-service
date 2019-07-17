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
import org.springframework.web.bind.annotation.RestController;

import com.lms.us.rest.model.json.UserRegistrationJson;
import com.lms.us.rest.service.UserRegistrationService;

@RestController
public class UserServiceRestController {
	private UserRegistrationService userRegistrationService;

	public UserServiceRestController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(userRegistrationService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId) {
		return new ResponseEntity<>(userRegistrationService.getUserById(userId), HttpStatus.OK);
	}

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addUser(@RequestBody UserRegistrationJson user) {
		return new ResponseEntity<>(userRegistrationService.addUser(user), HttpStatus.OK);
	}
	@DeleteMapping(value = "/users/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId) {
		return null;
	}
	@PutMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUser(@PathVariable("userId") String userId, @RequestBody UserRegistrationJson user) {
		return null;
	}

}
