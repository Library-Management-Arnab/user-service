package com.lms.us.rest.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "user_right")
public class UserRight {

	@Column(length = 20)
	@JsonIgnore
	private String userRightId;
	
	@Id
	@Column(name = "user_right_code", unique = true, length = 20)
	@JsonIgnore
	private String userRightCode;

	@Column(name="user_access_type", unique = true, length = 20)
	private String right;

	@Column(name="user_right_description", length = 200)
	private String description;
}
