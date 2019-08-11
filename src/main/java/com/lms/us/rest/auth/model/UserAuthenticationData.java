package com.lms.us.rest.auth.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lms.svc.common.model.AuthenticatedUser;

public class UserAuthenticationData implements Authentication {

	private static final long serialVersionUID = -1675601499842427538L;

	private AuthenticatedUser authenticatedUser;
	private String name;
	private boolean isAuthenticated;

	public UserAuthenticationData(AuthenticatedUser authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
		this.name = authenticatedUser.getUserName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authenticatedUser.getUserRights()
												.stream()
												.map(userRight -> new SimpleGrantedAuthority("ROLE_" + userRight))
												.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return authenticatedUser;
	}

	@Override
	public Object getPrincipal() {
		return authenticatedUser;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

}
