package com.lms.us.rest.transformer;

import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserJson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserDataTransformer {
    private StaticDataTransformer staticDataTransformer;
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

        userJson.setStatus(staticDataTransformer.getDescriptionFromUserStatus(userData.getLoginData().getStatus()));
        return userJson;
    }

    public List<UserJson> userDataListToUserJsonList(List<UserData> userDataList) {
        return userDataList
                        .stream()
                        .map(this::userDataToUserJson)
                        .collect(Collectors.toList());
    }
/*
    public List<UserData> userJsonListToUserDataList(List<UserJson> userJsonList) {
        return userJsonList
                        .stream()
                        .map(this::userJsonToUserData)
                        .collect(Collectors.toList());

    }
    */
}
