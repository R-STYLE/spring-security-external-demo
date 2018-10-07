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
	private static final String CREDENTIAL = "c";

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		LOGGER.info("test");
		return loadFromCookie(PRINCIPAL, request.getCookies());
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		LOGGER.info("test");
		return loadFromCookie(CREDENTIAL, request.getCookies());
	}

	public static String loadFromCookie(final String name, final Cookie[] cookies) {
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		for (Cookie c : cookies) {
			if (name.equals(c.getName())) {
				LOGGER.info(c.getValue());
				return c.getValue();
			}
		}
		return null;
	}
}
