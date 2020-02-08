package com.lms.us.rest.repository;

import com.lms.us.rest.model.auth.UserApiData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserApiRepository extends JpaRepository<UserApiData, String> {
    Optional<UserApiData> findByClientId(String clientId);
    Optional<UserApiData> findByUserId(String userId);
}
