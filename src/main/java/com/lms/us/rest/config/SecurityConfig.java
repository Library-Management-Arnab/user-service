package com.lms.us.rest.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lms.us.rest.auth.provider.UserServiceAuthenticationProvider;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// private static final String[] SECURED_RESOURCES = { "/login",
	// "/oauth/authorize" };

	private UserServiceAuthenticationProvider userServiceAuthenticationProvider;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		AuthenticationManager authenticationManager = new ProviderManager(Arrays.asList(userServiceAuthenticationProvider));
		return authenticationManager;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.disable()
			.authorizeRequests()
			.antMatchers("/users/")
			.permitAll()
			.antMatchers("/oauth/authorize")
			.permitAll()
            .antMatchers("/oauth/token")
            .permitAll()
            .antMatchers("/api-docs/**")
            .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userServiceAuthenticationProvider);
	}
}
