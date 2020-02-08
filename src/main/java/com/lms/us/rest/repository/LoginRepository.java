package com.lms.us.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.us.rest.model.db.LoginData;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginData, String>{
    Optional<LoginData> findByUserName(String userName);
}
