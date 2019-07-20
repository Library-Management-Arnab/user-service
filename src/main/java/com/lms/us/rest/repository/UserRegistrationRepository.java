package com.lms.us.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.us.rest.model.db.UserRegistrationData;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, String>{
	public Optional<UserRegistrationData> findByUserName(String userName);
}
