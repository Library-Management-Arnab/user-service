package com.lms.us.rest.model.db;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_registration")
public class UserRegistrationData implements Serializable {
	private static final long serialVersionUID = -1897451298385075865L;
	@Id
	@Column(length = 15)
	private String userId;

	@Column(unique = true, nullable = false, length = 12, updatable = false)
	private String userName;

	@Column(nullable = false, length = 30, unique = true)
	private String email;

	@Column(nullable = false, length = 30)
	private String registrationDate;

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
