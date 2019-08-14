package com.lms.us.rest.model.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDataJson implements Serializable {
    private static final long serialVersionUID = -23123983948912L;
    private String loginDataId;

    private String userName;

    private String userId;

    private String fullName;

    private String password;

    private String secret;

    private String status;

    private UserApiJson userApiJson;

}
