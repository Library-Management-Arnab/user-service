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

import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserDataJson;
import com.lms.us.rest.service.UserRegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserServiceRestController {
	private UserRegistrationService userRegistrationService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(userRegistrationService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> register(@RequestBody UserDataJson user) {
		return new ResponseEntity<>(userRegistrationService.register(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId) {
		return new ResponseEntity<>(userRegistrationService.getUserById(userId), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUser(@PathVariable("userId") String userId,
			@RequestBody UserData user) {
		return new ResponseEntity<>(userRegistrationService.updateUserAsSelf(userId, user), HttpStatus.OK);
	}

}
