package com.lms.us.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.us.rest.model.db.UserRight;

@Repository
public interface UserRightRepository extends JpaRepository<UserRight, String> {

	Optional<UserRight> findByRight(String right);

	@Query(value = "select user_access_type from user_right", nativeQuery = true)
	List<String> findAllRights();
}
