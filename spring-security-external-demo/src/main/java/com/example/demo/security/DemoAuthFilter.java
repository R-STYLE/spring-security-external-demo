package com.example.demo.security;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class DemoAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private static final String PRINCIPAL = "p";
	private static final String CREDENTIALS = "c";

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		final String principal = loadFromCookie(PRINCIPAL, request.getCookies());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[preauth][  principal] " + principal);
		}
		return principal;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		final String credentials = loadFromCookie(CREDENTIALS, request.getCookies());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[preauth][credentials] " + credentials);
		}
		return credentials;
	}

	public static String loadFromCookie(final String name, final Cookie[] cookies) {
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("[preauth] cookie: " + cookie);
			}
			if (name.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
