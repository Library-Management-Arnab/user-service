package com.lms.us.rest.model.auth;

import com.lms.us.rest.model.db.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "user_api_data")
public class UserAPIData implements Serializable {
    private static final long serialVersionUID = -92389423491923L;

    public UserAPIData() {
    }

    public UserAPIData(UserAPIData another) {
        this.recordId = another.recordId;
        this.clientId = another.clientId;
        this.userId = another.userId;
        this.secret = another.secret;
        this.scopes = another.scopes;
        this.userRoles = another.userRoles;
        this.accessTokenValiditySeconds = another.accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = another.refreshTokenValiditySeconds;
    }

    @Id
    @Column(name = "record_id", length = 25)
    private String recordId;

    @Column(length = 20, unique = true, nullable = false)
    private String userId;

    @Column(length = 20, unique = true, nullable = false)
    private String clientId;

    @Column(length = 200, nullable = false)
    private String secret;

    @Column(nullable = false)
    private Integer accessTokenValiditySeconds;

    @Column(nullable = false)
    private Integer refreshTokenValiditySeconds;

    @ManyToMany
    private Set<Scope> scopes;

    @ManyToMany
    private Set<UserRole> userRoles;
}
