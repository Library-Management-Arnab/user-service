package com.lms.us.rest.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.us.rest.config.StaticDataLoader;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.db.UserRight;
import com.lms.us.rest.model.db.UserStatus;
import com.lms.us.rest.model.json.UserJson;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserDataTransformer {
    private StaticDataLoader staticDataLoader;

    public UserData userJsonToUserData(UserJson userJson) {
        UserData userData = new UserData();
        
        userData.setPassword(userJson.getPassword());
        userData.setConfirmPassword(userJson.getConfirmPassword());
        
        userData.setAddress1(userJson.getAddress1());
        userData.setAddress2(userJson.getAddress2());
        userData.setContactNo(userJson.getContactNo());
        userData.setDisplayName(String.format("%s %s", userJson.getFirstName(), userJson.getLastName()));
        userData.setEmail(userJson.getEmail());
        userData.setFirstName(userJson.getFirstName());
        userData.setLastName(userJson.getLastName());
        userData.setLastUpdateDate(userJson.getLastUpdateDate());
        userData.setPin(userJson.getPin());
        userData.setRegistrationDate(userJson.getRegistrationDate());
        
        userData.setUserName(userJson.getUserName());
        
        userData.setStatus(getUserStatusFromDescription(userJson.getStatus()));
        userData.setUserRights(getUserRightsFromAccessTypes(userJson.getRights()));

        return userData;
    }

    public UserJson userDataToUserJson(UserData userData) {
        UserJson userJson = new UserJson();

        userJson.setAddress1(userData.getAddress1());
        userJson.setAddress2(userData.getAddress2());
        userJson.setContactNo(userData.getContactNo());
        userJson.setDisplayName(userData.getDisplayName());
        userJson.setEmail(userData.getEmail());
        userJson.setFirstName(userData.getFirstName());
        userJson.setLastName(userData.getLastName());
        userJson.setLastUpdateDate(userData.getLastUpdateDate());
        userJson.setPin(userData.getPin());
        userJson.setRegistrationDate(userData.getRegistrationDate());

        userJson.setUserName(userData.getUserName());
        userJson.setUserId(userData.getUserId());
        userJson.setStatus(getDescriptionFromUserStatus(userData.getStatus()));
        userJson.setRights(getAccessTypeFromUserRight(userData.getUserRights()));

        return userJson;
    }

    public List<UserJson> userDataListToUserJsonList(List<UserData> userDataList) {
        List<UserJson> userJsonList = new ArrayList<>();
        userDataList.forEach(userData -> {
            userJsonList.add(userDataToUserJson(userData));
        });
        return userJsonList;
    }

    public List<UserData> userJsonListToUserDataList(List<UserJson> userJsonList) {
        List<UserData> userDataList = new ArrayList<>();
        userJsonList.forEach(userJson -> {
            userDataList.add(userJsonToUserData(userJson));
        });
        return userDataList;
    }

    public UserStatus getUserStatusFromDescription(String description) {
        return staticDataLoader.getUserStatusFromDescription(description);
    }

    public String getDescriptionFromUserStatus(UserStatus userStatus) {
        return staticDataLoader.getDescriptionFromUserStatus(userStatus);
    }

    public List<UserRight> getUserRightsFromAccessTypes(List<String> rights) {
        return staticDataLoader.getUserRightsFromAccessTypes(rights);
    }

    public List<String> getAccessTypeFromUserRight(List<UserRight> userRights) {
        return staticDataLoader.getAccessTypesFromUserRights(userRights);
    }
}
