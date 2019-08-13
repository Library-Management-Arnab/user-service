package com.lms.us.rest.service;

import com.lms.us.rest.exception.NoSuchUserException;
import com.lms.us.rest.model.auth.UserAPIData;
import com.lms.us.rest.repository.UserAPIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAPIService {
    private UserAPIRepository userAPIRepository;

    public UserAPIData findByClientId(String clientId) {
        Optional<UserAPIData> found = userAPIRepository.findByClientId(clientId);
        return found.orElseThrow(NoSuchUserException::new);
    }
}
