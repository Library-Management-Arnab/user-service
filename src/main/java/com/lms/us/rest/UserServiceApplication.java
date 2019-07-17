package com.lms.us.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages= {"com.jpop.epam.batch2.team4.arnab"})
@EntityScan(basePackages = "com.jpop.epam.batch2.team4.arnab.us.rest.model.db")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
