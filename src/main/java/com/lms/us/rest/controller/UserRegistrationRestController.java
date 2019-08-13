package com.lms.us.rest.controller;

import com.lms.us.rest.model.json.UserJson;
import com.lms.us.rest.service.UserRegistrationService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(value = "User Registration Service Rest Controller")
public class UserRegistrationRestController {
    private UserRegistrationService userRegistrationService;

    @ApiOperation(value = "User registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class, httpMethod = "POST")
    @ApiResponses({ @ApiResponse(code = 201, message = "User successfully registered"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 403, message = "User not authorized to perform this operation"),
            @ApiResponse(code = 409, message = "Duplicate user."),
            @ApiResponse(code = 500, message = "Generic error") })
    @PostMapping(value = {"/register", "/register/"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> register(@RequestBody UserJson userJson) {
        return new ResponseEntity<>(userRegistrationService.register(userJson), HttpStatus.OK);
    }
}
