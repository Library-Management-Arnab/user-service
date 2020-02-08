package com.lms.us.rest.repository;

import com.lms.us.rest.model.auth.UserApiData;
import com.lms.us.rest.model.db.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserApiDataRepository extends JpaRepository<UserApiData, String> {
//    @Query(name = "select * from user_api_data inner join login_data on user_api_data.user_id=login_data.user_id where user_id=:userId",
//            nativeQuery = true)
    Optional<UserApiData> findByUserId(@Param("userId") String userId);
}
