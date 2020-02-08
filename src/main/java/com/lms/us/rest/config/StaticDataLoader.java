package com.lms.us.rest.config;

import com.lms.svc.common.config.BaseDataLoader;
import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.model.auth.Scope;
import com.lms.us.rest.model.auth.UserRole;
import com.lms.us.rest.model.db.UserStatus;
import com.lms.us.rest.repository.ScopeRepository;
import com.lms.us.rest.repository.UserRoleRepository;
import com.lms.us.rest.repository.UserStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Component
public class StaticDataLoader extends BaseDataLoader {
    private static final Logger LOG = LoggerFactory.getLogger(StaticDataLoader.class);

    private UserRoleRepository userRoleRepository;
    private UserStatusRepository userStatusRepository;
    private ScopeRepository scopeRepository;

    public StaticDataLoader(UserRoleRepository userRoleRepository, UserStatusRepository userStatusRepository, ScopeRepository scopeRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userStatusRepository = userStatusRepository;
        this.scopeRepository = scopeRepository;
    }

    private Collection<UserRole> userRoles;
    private Collection<String> allRoles;
    private Collection<UserStatus> userStatuses;
    private Collection<String> allStatuses;
    private Collection<String> allScopeNames;
    private Collection<Scope> allScopes;

    @PostConstruct
    public void populateStaticData() {
        LOG.info("Populating static data");
        userRoles = userRoleRepository.findAll();
        userStatuses = userStatusRepository.findAll();
        allScopes = scopeRepository.findAll();

        allRoles = CommonUtil.toCollection(userRoles, UserRole::getRoleCode);
        allStatuses = CommonUtil.toCollection(userStatuses, UserStatus::getStatusDescription);
        allScopeNames = CommonUtil.toCollection(allScopes, Scope::getScopeName);
    }

    public Collection<UserRole> getRoles(Collection<String> roleCodes) {
        List<UserRole> roles = new ArrayList<>();
        roleCodes.forEach(roleCode -> {
            Predicate<UserRole> userRolePredicate = role -> role.getRoleCode().equalsIgnoreCase(roleCode);
            roles.add(returnOrThrow(userRoles, userRolePredicate, roleCode, allRoles, "User Role"));
        });
        return roles;
    }

    public Collection<String> getRoleCodes(Collection<UserRole> roles) {
        List<String> roleCodes = new ArrayList<>();
        roles.forEach(role -> roleCodes.add(getClientString(roles, role, UserRole::getRoleCode)));
        return roleCodes;
    }

    public UserStatus getUserStatusFromDescription(String description) {
        Predicate<UserStatus> userStatusPredicate = userStatus -> userStatus.getStatusDescription()
                .equalsIgnoreCase(description);
        return returnOrThrow(userStatuses, userStatusPredicate, description, allStatuses, "UserStatus");
    }

    public String getDescriptionFromUserStatus(UserStatus userStatus) {
        return getClientString(userStatuses, userStatus, UserStatus::getStatusDescription);
    }

    public Collection<Scope> toScopes(Collection<String> scopeNames) {
        List<Scope> scopes = new ArrayList<>();
        scopeNames.forEach(scopeName -> {
            Predicate<Scope> scopePredicate = scope -> scope.getScopeName().equalsIgnoreCase(scopeName);
            scopes.add(returnOrThrow(allScopes, scopePredicate, scopeName, allScopeNames, "Scope"));
        });
        return scopes;
    }

    public Collection<String> fromScopes(Collection<Scope> scopes) {
        List<String> scopeNames = new ArrayList<>();
        scopes.forEach(scope -> scopeNames.add(getClientString(scopes, scope, Scope::getScopeName)));
        return scopeNames;
    }

}
