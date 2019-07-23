package com.lms.us.rest.model.db;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lms.svc.common.constants.ApplicationCommonConstants;

import lombok.Data;

@Data
@Entity
@Table(name = "user_data")
public class UserData implements Serializable {
	private static final long serialVersionUID = -1897451298385075865L;
	public UserData() {
		this.setUserId("U" + ApplicationCommonConstants.generateId());
	}
	
	@Id
	@Column(length = 30)
	private String userId;

	@Column(unique = true, nullable = false, length = 12, updatable = false)
	private String userName;
	
	// This field is not mapped to a column
	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	// This field is not mapped to a column
	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	private String confirmPassword;

	@Column(nullable = false, length = 30, unique = true)
	private String email;

	@Column(nullable = false, length = 30)
	private String registrationDate;
	
	@Column(nullable = false, length = 30)
	private String lastUpdateDate;

	@Column(nullable = false, length = 30)
	private String firstName;

	@Column(nullable = false, length = 30)
	private String lastName;

	@Column(nullable = false, length = 30)
	private String address1;

	@Column(nullable = true, length = 30)
	private String address2;

	@Column(nullable = false, length = 6)
	private int pin;

	@Column(nullable = false, length = 20, unique = true)
	private String contactNo;

	@Column(nullable = false, length = 30)
	private String displayName;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private LoginData loginData;
	
	@OneToOne
	@JoinColumn(name="user_right_code", referencedColumnName="user_right_code")
	private UserRight userRight;
	
	@OneToOne
	@JoinColumn(name="status_code", referencedColumnName="status_code")
	private UserStatus status;
}
