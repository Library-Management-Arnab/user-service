package com.lms.us.rest.model.db;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

//	@Column(nullable = false, length = 2)
//	private String userAccessType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private LoginData loginData;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_group_mapping", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="group_id")})
	private List<AccessGroup> accessGroups;
}
