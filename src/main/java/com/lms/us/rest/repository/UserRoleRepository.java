package com.lms.us.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.us.rest.model.auth.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

	Optional<UserRole> findByRoleName(String right);

	@Query(value = "select user_access_type from user_right", nativeQuery = true)
	List<String> findAllRoles();
}
