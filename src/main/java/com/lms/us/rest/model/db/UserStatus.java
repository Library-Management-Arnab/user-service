package com.lms.us.rest.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_status")
public class UserStatus implements Serializable {
	private static final long serialVersionUID = -6166859757961816457L;

	@Id
	@Column(name = "status_code", length = 2)
	private String statusCode;

	@Column(length = 20, nullable = false)
	private String statusDescription;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserStatus that = (UserStatus) o;
		return statusCode.equals(that.statusCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusCode);
	}
}
