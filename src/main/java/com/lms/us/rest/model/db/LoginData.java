package com.lms.us.rest.model.db;

import java.io.Serializable;

import javax.persistence.*;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import com.lms.svc.common.util.CommonUtil;
import com.lms.us.rest.model.auth.UserApiData;
import lombok.Data;

@Data
@Entity
@Table(name = "login_data")
public class LoginData implements Serializable {
	private static final long serialVersionUID = -1873177848918732842L;

	public LoginData() {
        this.loginDataId = String.format("LOGIN%s", CommonUtil.generateId());
    }

    @Id
    @Column(name = "login_data_id   ", length = 30)
    private String loginDataId;

	@Column(name = "user_name", length = 12)
	private String userName;

	@Column(name = "user_id", nullable = false, length = 30, unique = true)
	private String userId;

	@Column(nullable = false, length = 100)
	private String fullName;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, length = 50)
	private String secret;

	@OneToOne
	@JoinColumn(name = "status_code", referencedColumnName = "status_code")
	private UserStatus status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_api_data_id", referencedColumnName = "record_id")
	private UserApiData userApiData;

	@OneToOne(mappedBy = "loginData")
	private UserData userData;
}
