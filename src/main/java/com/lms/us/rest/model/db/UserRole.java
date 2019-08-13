package com.lms.us.rest.model.db;

import com.lms.us.rest.model.auth.UserAPIData;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_api_role_mapping",
					joinColumns = @JoinColumn(name = "user_api_record_id"),
					inverseJoinColumns = @JoinColumn(name = "user_role_id"))
	private List<UserAPIData> users;

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
