package com.lms.us.rest.model.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_right")
public class UserRight {

	@Id
	@Column(name = "user_right_id", unique = true, length = 20)
	@JsonIgnore
	private String userRightId;

	@Column(name = "user_right_code", unique = true, length = 30)
	@JsonIgnore
	private String userRightCode;

	@Column(name = "user_access_type", unique = true, length = 50)
	private String right;

	@Column(name = "user_right_description", length = 200)
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usre_role_mapping", 
					joinColumns = @JoinColumn(name = "user_name"), 
					inverseJoinColumns = @JoinColumn(name = "user_right_id"))
	private List<UserData> users;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserRight userRight = (UserRight) o;
		return userRightCode.equals(userRight.userRightCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userRightCode);
	}
}
