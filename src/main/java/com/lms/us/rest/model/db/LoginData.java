package com.lms.us.rest.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "login_data")
public class LoginData implements Serializable {
	private static final long serialVersionUID = -1873177848918732842L;

	@Id
	@Column(length = 12)
	private String userName;
	
	@Column(nullable = false, length = 15)
	private String userId;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, length = 50)
	private String secret;
	
	@OneToOne
	@JoinColumn(name="status_code", referencedColumnName="status_code")
	private UserStatus status;
	
	@OneToOne
	@JoinColumn(name="user_right_code", referencedColumnName="user_right_code")
	private UserRight userRight;
}
