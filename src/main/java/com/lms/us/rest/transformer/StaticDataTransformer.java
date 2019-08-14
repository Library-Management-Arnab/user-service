package com.lms.us.rest.transformer;

import com.lms.us.rest.config.StaticDataLoader;
import com.lms.us.rest.model.auth.Scope;
import com.lms.us.rest.model.auth.UserRole;
import com.lms.us.rest.model.db.UserStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class StaticDataTransformer {
    private StaticDataLoader staticDataLoader;

    public UserStatus getUserStatusFromDescription(String description) {
        return staticDataLoader.getUserStatusFromDescription(description);
    }

    public String getDescriptionFromUserStatus(UserStatus userStatus) {
        if(userStatus == null) {
            return "NO_STATUS";
        }
        return staticDataLoader.getDescriptionFromUserStatus(userStatus);
    }

    public Set<UserRole> getRoles(Collection<String> roles) {
        return new HashSet<>(staticDataLoader.getRoles(roles));
    }

    public Set<String> getRoleCodes(Collection<UserRole> userRoles) {
        return new HashSet<>(staticDataLoader.getRoleCodes(userRoles));
    }

    public Set<String> getScopeNames(Collection<Scope> scopes) {
        return new HashSet<>(staticDataLoader.fromScopes(scopes));
    }
    public Set<Scope> getScopes(Collection<String> scopeNames) {
        return new HashSet<>(staticDataLoader.toScopes(scopeNames));
    }
}
