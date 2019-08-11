package com.lms.us.rest.model.auth;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lms.us.rest.model.db.UserData;

public class AuthenticatedUser extends UserData implements UserDetails {
	private static final long serialVersionUID = 2043868856866759851L;

	public AuthenticatedUser(final UserData userData) {
		super(userData);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getUserRights()
							.stream()
							.map(userRight -> new SimpleGrantedAuthority("ROLE" + userRight.getUserRightCode()))
							.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
