package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
@EnableWebSecurity
public class DemoAuthConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(preAuthenticatedProcessingFilter());
		http.authenticationProvider(preAuthenticatedAuthenticationProvider());
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
	}

	@Bean
	public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
		return new DemoAuthService();
	}

	@Bean
	public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
		provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
		return provider;
	}

	@Bean
	public AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception {
		final DemoAuthFilter filter = new DemoAuthFilter();
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

}
