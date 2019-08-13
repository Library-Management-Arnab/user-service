package com.lms.us.rest.auth.model;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.us.rest.model.auth.Scope;
import com.lms.us.rest.model.auth.UserAPIData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

public class ApiClientDetails extends UserAPIData implements ClientDetails {

    public ApiClientDetails(UserAPIData userAPIData) {
        super(userAPIData);
    }

    @Override
    public String getClientId() {
        return super.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return super.getSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(ApplicationCommonConstants.toCollection(super.getScopes(), Scope::getScopeName));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return ApplicationCommonConstants.toCollection(super.getUserRoles(), role -> new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return super.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return super.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }

}
