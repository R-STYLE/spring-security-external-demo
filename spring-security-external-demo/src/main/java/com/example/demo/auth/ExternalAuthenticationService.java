package com.example.demo.auth;

import org.springframework.stereotype.Component;

import com.example.demo.security.DemoRole;

@Component
public class ExternalAuthenticationService {

	public boolean isAuthorized(Object principal, Object credentials) {
		return principal == null || credentials == null;
	}

	public ExternalAuthenticated authenticate(Object principal, Object credentials) {
		// dummy code.
		return ExternalAuthenticated.builder()
				.account("demo")
				.email("demo@example.com")
				.type(DemoRole.ADMIN.getType())
				.build();
	}
}
