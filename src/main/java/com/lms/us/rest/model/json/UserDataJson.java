package com.lms.us.rest.model.json;

import java.io.Serializable;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lms.us.rest.model.db.LoginData;
import com.lms.us.rest.model.db.UserRight;
import com.lms.us.rest.model.db.UserStatus;

import lombok.Data;

@Data
public class UserDataJson implements Serializable {
	private static final long serialVersionUID = -14593549320932492L;

	public UserDataJson() {
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	private String userId;

	private String userName;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String confirmPassword;

	private String email;

	private String registrationDate;

	private String lastUpdateDate;

	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private int pin;

	private String contactNo;

	private String displayName;

	@JsonIgnore
	private LoginData loginData;

	@OneToOne
	private UserRight userRight;

	@OneToOne
	private UserStatus status;
}
