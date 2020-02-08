package com.lms.us.rest.model.auth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@Column(name = "user_role_id", unique = true, length = 30)
	private String roleId;

	@Column(name = "user_role_code", unique = true, length = 30)
	private String roleCode;

	@Column(name = "user_role_name", unique = true, length = 50)
	private String roleName;

	@Column(name = "user_role_description", length = 200)
	private String description;

//	private List<UserAPIData> userAPIDataList;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserRole userrole = (UserRole) o;
		return Objects.equals(roleCode, userrole.roleCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleCode);
	}
}
