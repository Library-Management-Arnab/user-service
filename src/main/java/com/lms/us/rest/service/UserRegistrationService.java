package com.lms.us.rest.service;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.crypto.CryptographyUtil;
import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.exception.DuplicateUserException;
import com.lms.us.rest.model.auth.UserApiData;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserJson;
import com.lms.us.rest.repository.UserRegistrationRepository;
import com.lms.us.rest.transformer.StaticDataTransformer;
import com.lms.us.rest.transformer.UserDataTransformer;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserRegistrationService {
    private LoginService loginService;
    private UserService userService;
    private UserDataTransformer userDataTransformer;
    private UserRegistrationRepository userRegistrationRepository;
    private StaticDataTransformer staticDataTransformer;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserJson register(UserJson userJson) {
        validateDuplicateUser(userJson.getUserName());
        String currentDate = CommonUtil.getCurrentDateAsString();
        userJson.setRegistrationDate(currentDate);
        userJson.setLastUpdateDate(currentDate);

        UserData registrationData = userDataTransformer.userJsonToUserData(userJson);

        LoginData loginData = extractLoginData(registrationData);
        registrationData.setLoginData(loginData);

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
        LoginData loginData = userData.getLoginData();
        loginData.setUserName(userData.getUserName());
        loginData.setPassword(userData.getPassword());
        loginData.setFullName(buildFullName(userData.getFirstName(), userData.getLastName()));
        loginData.setUserId(userData.getUserId());

        setEncryptedPassword(loginData);

        loginData.setStatus(staticDataTransformer.getUserStatusFromDescription(ApplicationCommonConstants.USER_STATUS_ACTIVE));

        updateUserApiData(loginData);
        return loginData;
    }
    private String buildFullName(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder();
        if(!StringUtils.isEmpty(firstName)) {
            sb.append(firstName);
        }
        if(!StringUtils.isEmpty(lastName)) {
            sb.append(" ");
            sb.append(lastName);
        }
        return sb.toString();
    }

    private void setEncryptedPassword(LoginData loginData) {
        String secret = CryptographyUtil.createSecret();

        String encryptedPassword = CryptographyUtil.encrypt(loginData.getPassword(), secret);

        loginData.setSecret(secret);
        loginData.setPassword(encryptedPassword);
    }

    private void updateUserApiData(LoginData loginData) {
        UserApiData userAPIData = new UserApiData();
        userAPIData.setUserId(loginData.getUserId());
        userAPIData.setClientId(String.format("%s%s",RandomStringUtils.randomAlphanumeric(5).toUpperCase(), loginData.getUserName().toUpperCase()));
        userAPIData.setSecret(CryptographyUtil.createSecret());

        userAPIData.setAccessTokenValiditySeconds(ApplicationCommonConstants.DEFAULT_ACCESS_TOKEN_VALIDITY_SECONDS);
        userAPIData.setRefreshTokenValiditySeconds(ApplicationCommonConstants.DEFAULT_REFRESH_TOKEN_VALIDITY_SECONDS);

        userAPIData.setUserRoles(staticDataTransformer.getRoles(Arrays.asList(ApplicationCommonConstants.USER_ROLE_BASIC)));
        userAPIData.setScopes(staticDataTransformer.getScopes(Arrays.asList(ApplicationCommonConstants.SCOPE_READ_ONLY)));

        loginData.setUserApiData(userAPIData);
    }

}
