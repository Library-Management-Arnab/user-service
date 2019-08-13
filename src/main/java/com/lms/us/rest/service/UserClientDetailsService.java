package com.lms.us.rest.service;

import com.lms.us.rest.auth.model.ApiClientDetails;
import com.lms.us.rest.model.auth.UserAPIData;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserClientDetailsService implements ClientDetailsService {
    private UserAPIService userAPIService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        UserAPIData foundClient = userAPIService.findByClientId(clientId);
        return new ApiClientDetails(foundClient);
    }

}
