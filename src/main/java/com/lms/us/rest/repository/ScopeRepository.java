package com.lms.us.rest.repository;

import com.lms.us.rest.model.auth.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, String> {
}
