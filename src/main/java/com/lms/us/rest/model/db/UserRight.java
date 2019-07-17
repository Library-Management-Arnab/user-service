package com.lms.us.rest.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_right")
public class UserRight {

	@Column(length = 20)
	private String userRightId;
	
	@Id
	@Column(name = "user_right_code", unique = true, length = 20)
	private String userRightCode;

	@Column(length = 200)
	private String userRightDescription;

	@Column(unique = true, length = 20)
	private String userAccessType;

}
