package com.lms.us.rest.model.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "access_group")
public class AccessGroup {
	@Id
	@Column(length = 20)
	private String groupId;

	@Column(unique = true, length = 20)
	private String groupCode;

	@Column(length = 200)
	private String groupDescription;

	@Column(unique = true, length = 20)
	private String groupAccessType;

	@ManyToMany(mappedBy = "accessGroups")
	private List<UserRegistrationData> users;
}
