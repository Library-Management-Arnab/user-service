package com.lms.us.rest.model.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User model for API request and response")
public class UserJson implements Serializable {
	private static final long serialVersionUID = -14593549320932492L;

	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(dataType = "String", notes = "UserId will not be read from API input. It will display the value from the database.", example = "U123456789012345", allowEmptyValue = false)
	private String userId;
	
	@ApiModelProperty(dataType = "String", notes = "UserName", example = "arnab001", required = true, allowEmptyValue = false)
	private String userName;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ApiModelProperty(dataType = "String", notes = "Password will only be read from user input. It is not part of response.", example = "pass123$", required = true, allowEmptyValue = false)
	private String password;

	@ApiModelProperty(dataType = "String", notes = "Confirm Password will only be read from user input. It is not part of response.", example = "pass123$", required = true, allowEmptyValue = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String confirmPassword;
	
	@ApiModelProperty(dataType = "String", notes = "Email address of the user", example = "abcde1234@gmail.com", required = true, allowEmptyValue = false)
	private String email;

	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(dataType = "String", notes = "Registration Date will not be read from API input. It will display the value from the database.", allowEmptyValue = false)
	private String registrationDate;

	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(dataType = "String", notes = "Last Update Date will not be read from API input. It will display the value from the database.", allowEmptyValue = false)
	private String lastUpdateDate;

	@ApiModelProperty(dataType = "String", notes = "First Name of the user", example = "Arnab", required = true, allowEmptyValue = false)
	private String firstName;

	@ApiModelProperty(dataType = "String", notes = "Last Name of the user", example = "Mondal", required = true, allowEmptyValue = false)
	private String lastName;

	@ApiModelProperty(dataType = "String", notes = "Address line 1 of the user", example = "Hyderabad Telangana", required = true, allowEmptyValue = false)
	private String address1;

	@ApiModelProperty(dataType = "String", notes = "Address line 2 of the user", example = "India", required = true, allowEmptyValue = false)
	private String address2;

	@ApiModelProperty(dataType = "int", notes = "Pin code of the user", example = "546890", required = true, allowEmptyValue = false)
	private int pin;

	@ApiModelProperty(dataType = "String", notes = "Contact No of the user", example = "546890", required = true, allowEmptyValue = false)
	private String contactNo;

	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(dataType = "String", notes = "Display name will not be read from API input. It will display the value from the database.", allowEmptyValue = false)
	private String displayName;

	@ApiModelProperty(dataType = "String", notes = "Access right of the user", example = "Basic User", required = true, allowEmptyValue = false)
	private String right;

	@ApiModelProperty(dataType = "String", notes = "Status of the user", example = "Active", required = true, allowEmptyValue = false)
	private String status;
}
