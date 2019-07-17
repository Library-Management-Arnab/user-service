package com.lms.us.rest.model.json;

import java.util.List;

import com.lms.us.rest.model.db.AccessGroup;

import lombok.Data;

@Data
public class UserRegistrationJson {
	private String userId;
	private String userName;
	private String password;
	private String confirmPassword;
	private String email;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private int pin;
	private String contactNo;
	private String displayName;
	private List<AccessGroup> accessGroups;
}
