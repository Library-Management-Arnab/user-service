package com.lms.us.rest.repository;

import com.lms.us.rest.model.auth.UserAPIData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAPIRepository extends JpaRepository<UserAPIData, String> {
    public Optional<UserAPIData> findByClientId(String clientId);
}
