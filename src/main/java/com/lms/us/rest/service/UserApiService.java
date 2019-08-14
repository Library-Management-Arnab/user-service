package com.lms.us.rest.service;

import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.auth.UserApiData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserApiJson;
import com.lms.us.rest.repository.UserApiRepository;
import com.lms.us.rest.transformer.UserApiTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserApiService {
    private UserApiRepository userAPIRepository;
    private UserService userService;
    private UserApiTransformer userApiTransformer;

    public UserApiJson findByClientId(String clientId) {
        Optional<UserApiData> found = userAPIRepository.findByClientId(clientId);
        return userApiTransformer.toUserApiJson(found.orElseThrow(NoSuchUserException::new));
    }

    public UserApiJson findByUserName(String userName) {
        UserData userData = userService.searchByUsername(userName);
        Optional<UserApiData> found = userAPIRepository.findByUserId(userData.getUserId());
        return userApiTransformer.toUserApiJson(found.orElseThrow(NoSuchUserException::new));
    }
}
