package com.lms.us.rest.transformer;

import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.model.auth.UserApiData;
import com.lms.us.rest.model.json.UserApiJson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserApiTransformer {
    private StaticDataTransformer staticDataTransformer;

    public UserApiData toUserApiData(final UserApiJson userApiJson) {
        UserApiData userApiData = new UserApiData();
        userApiData.setAccessTokenValiditySeconds(userApiJson.getAccessTokenValiditySeconds());
        userApiData.setClientId(userApiJson.getClientId());
        userApiData.setRefreshTokenValiditySeconds(userApiJson.getRefreshTokenValiditySeconds());
        userApiData.setScopes(staticDataTransformer.getScopes(userApiJson.getScopes()));
        userApiData.setUserRoles(staticDataTransformer.getRoles(userApiJson.getUserRoles()));
        return userApiData;
    }

    public UserApiJson toUserApiJson(final UserApiData userApiData) {
        UserApiJson userApiJson = new UserApiJson();
        userApiJson.setRecordId(CommonUtil.mask(userApiData.getRecordId()));
        userApiJson.setUserId(CommonUtil.mask(userApiData.getUserId()));
        userApiJson.setAccessTokenValiditySeconds(userApiData.getAccessTokenValiditySeconds());
        userApiJson.setClientId(CommonUtil.mask(userApiData.getClientId()));
        userApiJson.setSecret(CommonUtil.mask(userApiData.getSecret()));
        userApiJson.setRefreshTokenValiditySeconds(userApiData.getRefreshTokenValiditySeconds());
        userApiJson.setScopes(staticDataTransformer.getScopeNames(userApiData.getScopes()));
        userApiJson.setUserRoles(staticDataTransformer.getRoleCodes(userApiData.getUserRoles()));
        return userApiJson;
    }

}
