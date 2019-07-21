package com.lms.us.rest.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "user_status")
public class UserStatus implements Serializable {
	private static final long serialVersionUID = -6166859757961816457L;

	@Id
	@Column(name = "status_code", length = 2)
	@JsonIgnore
	private String statusCode;

	@Column(length = 20, nullable = false)
	private String statusDescription;
}
