package com.lms.us.rest.service;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.db.UserData;
import com.lms.us.rest.model.json.UserJson;
import com.lms.us.rest.repository.UserRegistrationRepository;
import com.lms.us.rest.transformer.StaticDataTransformer;
import com.lms.us.rest.transformer.UserDataTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRegistrationRepository userRegistrationRepository;
    private UserDataTransformer userDataTransformer;
    private StaticDataTransformer staticDataTransformer;

    public List<UserJson> getAllUsers() {
        List<UserData> allUsers = userRegistrationRepository.findAll();
        return userDataTransformer.userDataListToUserJsonList(allUsers);
    }

    public UserJson getUserById(String userId) {
        UserData foundUser = searchAndValidateByUserId(userId);
        return userDataTransformer.userDataToUserJson(foundUser);
    }

    public UserJson getByUserName(String userName) {
        UserData foundUser = searchAndValidateByUserName(userName);
        return userDataTransformer.userDataToUserJson(foundUser);
    }
    public void deleteUser(String userId) {
        UserData user = searchUserById(userId);
        user.getLoginData().setStatus(staticDataTransformer.getUserStatusFromDescription(ApplicationCommonConstants.USER_STATUS_CODE_DELETED));
        user.setLastUpdateDate(CommonUtil.getCurrentDateAsString());
        userRegistrationRepository.save(user);
    }

    public UserJson updateUserAsSelf(String userId, UserJson userJson) {
        UserData existing = searchUserById(userId);

        UserData input = userDataTransformer.userJsonToUserData(userJson);

        //Restoring the existing userId
        input.setUserId(existing.getUserId());

        // Basic user cannot update its own status
        input.getLoginData().setStatus(existing.getLoginData().getStatus());

        // Basic user cannot update its own rights
        //input.setUserRights(existing.getUserRights());

        // Registration date is unchangeable
        input.setRegistrationDate(existing.getRegistrationDate());

        input.setLastUpdateDate(CommonUtil.getCurrentDateAsString());

        UserData updatedUser = userRegistrationRepository.save(input);

        return userDataTransformer.userDataToUserJson(updatedUser);
    }

    // TODO - To be added later
    public UserJson updateUserAsAdmin(String userId, UserJson userJson) {
        UserData existing = searchUserById(userId);

        UserData input = userDataTransformer.userJsonToUserData(userJson);

        // Registration date is unchangeable
        input.setRegistrationDate(existing.getRegistrationDate());
        input.setLastUpdateDate(CommonUtil.getCurrentDateAsString());

        UserData updatedUser = userRegistrationRepository.save(input);
        return userDataTransformer.userDataToUserJson(updatedUser);
    }

    private UserData searchUserById(String userId) {
        Optional<UserData> searchResult = userRegistrationRepository.findById(userId);
        return searchResult.isPresent() ? searchResult.get() : null;
    }
    private UserData searchAndValidateByUserId(String userId) {
        UserData foundUser = searchUserById(userId);
        if (foundUser != null) {
            return foundUser;
        }
        throw new NoSuchUserException();
    }
    private UserData searchAndValidateByUserName(String userName) {
        UserData foundUser = searchByUsername(userName);
        if (foundUser != null) {
            return foundUser;
        }
        throw new NoSuchUserException();
    }
    public UserData searchByUsername(String userName) {
        Optional<UserData> searchResult = userRegistrationRepository.findByUserName(userName);
        return searchResult.isPresent() ? searchResult.get() : null;
    }
}
