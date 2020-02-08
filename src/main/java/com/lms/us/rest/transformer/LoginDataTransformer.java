package com.lms.us.rest.transformer;

import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.json.LoginDataJson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginDataTransformer {
    private StaticDataTransformer staticDataTransformer;
    private UserApiTransformer userApiTransformer;

    public LoginData toLoginData(LoginDataJson loginDataJson) {
        LoginData loginData = new LoginData();
        loginData.setStatus(staticDataTransformer.getUserStatusFromDescription(loginDataJson.getStatus()));
        loginData.setFullName(loginDataJson.getFullName());
        loginData.setLoginDataId(loginDataJson.getLoginDataId());
        // loginData.setPassword(CommonUtil.mask("1234567890", 10));
       // loginData.setSecret(CommonUtil.mask("1234567890", 10));
        loginData.setUserApiData(userApiTransformer.toUserApiData(loginDataJson.getUserApiJson()));
        loginData.setUserId(loginDataJson.getUserId());
        loginData.setUserName(loginDataJson.getUserName());

        return loginData;
    }

    public LoginDataJson toLoginDataJson(LoginData loginData) {
        LoginDataJson loginDataJson = new LoginDataJson();
        loginDataJson.setStatus(staticDataTransformer.getDescriptionFromUserStatus(loginData.getStatus()));
        loginDataJson.setFullName(loginData.getFullName());
        loginDataJson.setLoginDataId(loginData.getLoginDataId());
        loginDataJson.setLoginDataId(CommonUtil.mask(loginData.getLoginDataId()));
        loginDataJson.setPassword(CommonUtil.mask("1234567890", 10));
        loginDataJson.setSecret(CommonUtil.mask("1234567890", 10));

        loginDataJson.setUserApiJson(userApiTransformer.toUserApiJson(loginData.getUserApiData()));

        loginDataJson.setUserId(CommonUtil.mask(loginData.getUserId()));
        loginDataJson.setUserName(loginData.getUserName());

        return loginDataJson;
    }
}
