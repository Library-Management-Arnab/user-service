package com.lms.us.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.us.rest.model.db.UserData;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserData, String>{
	public Optional<UserData> findByUserName(String userName);
	//@Query(value = "select user_right_id, user_right_code, user_right_description, user_access_type from ")
	//public List<UserRight> findUserRightsByUserName(@Param("userName") String userName);
}
