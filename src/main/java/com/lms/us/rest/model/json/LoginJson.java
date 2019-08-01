package com.lms.us.rest.model.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Login model for API request and response")
public class LoginJson {
	
	@ApiModelProperty(dataType = "String", notes = "UserName", example = "arnab001", required = true, allowEmptyValue = false)
	private String userName;
	@ApiModelProperty(dataType = "String", notes = "Password", example = "pass123$", required = true, allowEmptyValue = false)
	private String password;
}
