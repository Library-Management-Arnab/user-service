package com.lms.us.rest.config;

import com.lms.us.rest.service.LoginService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
	private LoginService loginService;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return loginService;
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		LOG.info("Configuring HTTP security patterns");
		http
			.authorizeRequests()
			.antMatchers("/register", "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.userDetailsService(loginService);
	}
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(loginService).passwordEncoder(passwordEncoder());
	}
}
