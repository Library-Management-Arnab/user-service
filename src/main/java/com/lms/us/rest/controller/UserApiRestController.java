package com.lms.us.rest.controller;

import com.lms.us.rest.service.LoginService;
import com.lms.us.rest.service.UserApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
@AllArgsConstructor
public class UserApiRestController {
    private UserApiService userAPIService;
    private LoginService loginService;

    @GetMapping(value = "/apiinfo/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getApiInfoForUser(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userAPIService.findByUserName(userName), HttpStatus.OK);
    }
    @GetMapping(value = "/logininfo/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getLoginInfo(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(loginService.getLoginDataJson(userName), HttpStatus.OK);
    }
}
