package com.lms.us.rest.service;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.us.rest.exception.DuplicateUserException;
import com.lms.us.rest.model.auth.UserAPIData;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserJson;
import com.lms.us.rest.repository.UserRegistrationRepository;
import com.lms.us.rest.transformer.UserDataTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserRegistrationService {
    private LoginService loginService;
    private UserService userService;
    private UserDataTransformer userDataTransformer;
    private UserRegistrationRepository userRegistrationRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public UserJson register(UserJson userJson) {
        validateDuplicateUser(userJson.getUserName());
        String currentDate = ApplicationCommonConstants.getCurrentDateAsString();
        userJson.setRegistrationDate(currentDate);
        userJson.setLastUpdateDate(currentDate);
        userJson.setStatus(ApplicationCommonConstants.USER_STATUS_ACTIVE);

        // TODO - to be separated for admin and user later
        userJson.setRights(Arrays.asList(ApplicationCommonConstants.USER_ROLE_BASIC));

        UserData registrationData = userDataTransformer.userJsonToUserData(userJson);

        LoginData loginData = extractLoginData(registrationData);
        loginData.setStatus(registrationData.getStatus());

        loginService.updateLoginInfo(loginData);
        registrationData.setLoginData(loginData);

        UserAPIData userAPIData = new UserAPIData();
        userAPIData.setUserId(registrationData.getUserId());
        userAPIData.setClientId(String.format("CLIENT%s", userJson.getUserName().toUpperCase()));
        userAPIData.setSecret(loginService.createSecret());

        userAPIData.setAccessTokenValiditySeconds(ApplicationCommonConstants.DEFAULT_ACCESS_TOKEN_VALIDITY_SECONDS);
        userAPIData.setRefreshTokenValiditySeconds(ApplicationCommonConstants.DEFAULT_REFRESH_TOKEN_VALIDITY_SECONDS);

        userAPIData.setUserRoles(userDataTransformer.getRoles(Arrays.asList(ApplicationCommonConstants.USER_ROLE_BASIC)));
        userAPIData.setScopes(userDataTransformer.getScopes(Arrays.asList(ApplicationCommonConstants.SCOPE_READ_ONLY)));
        registrationData.setUserAPIData(userAPIData);

        UserData registeredUser = userRegistrationRepository.save(registrationData);
        return userDataTransformer.userDataToUserJson(registeredUser);
    }

    private void validateDuplicateUser(String userName) {
        UserData found = userService.searchByUsername(userName);
        if (found != null && found.getUserId() != null) {
            throw new DuplicateUserException(userName);
        }
    }

    private LoginData extractLoginData(UserData userData) {
        LoginData loginData = new LoginData();
        loginData.setUserName(userData.getUserName());
        loginData.setPassword(userData.getPassword());
        loginData.setUserId(userData.getUserId());
        loginData.setStatus(userData.getStatus());
        return loginData;
    }
}
