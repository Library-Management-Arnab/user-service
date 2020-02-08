package com.lms.us.rest.model.auth;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.model.db.LoginData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "user_api_data")
public class UserApiData implements Serializable {
    private static final long serialVersionUID = -92389423491923L;

    public UserApiData() {
        this.recordId = String.format("USRAPI%s", CommonUtil.generateId());
    }

    public UserApiData(UserApiData another) {
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

    @OneToOne(mappedBy = "userApiData")
    private LoginData loginData;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "api_data_scope_mapping",
            joinColumns = @JoinColumn(name = "api_record_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<Scope> scopes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_api_role_mapping",
            joinColumns = @JoinColumn(name = "user_api_record_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<UserRole> userRoles;
}
