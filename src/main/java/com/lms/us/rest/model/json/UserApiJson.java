package com.lms.us.rest.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.us.rest.model.auth.Scope;
import com.lms.us.rest.model.auth.UserRole;
import com.lms.us.rest.model.db.LoginData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class UserApiJson implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String recordId;
    private String userId;
    private String clientId;
    private String secret;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Set<String> scopes;
    private Set<String> userRoles;
}
