package com.example.demo.controller;

import java.lang.invoke.MethodHandles;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.security.DemoRole;
import com.example.demo.security.DemoUser;

@Controller
public class PageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(HttpServletResponse res, Model model) {
		res.addCookie(new Cookie("p", "name"));
		res.addCookie(new Cookie("c", "credentials"));
		return "index";
	}

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test(Principal principal, Model model) {
		final Authentication authentication = (Authentication) principal;
		final DemoUser user = (DemoUser) authentication.getPrincipal();
		LOGGER.info("[user] " + ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
		model.addAttribute("user", user);
		model.addAttribute("isAdmin", DemoRole.isAdmin(user.getAuthorities()));
		model.addAttribute("isOperator", DemoRole.isAdmin(user.getAuthorities()));
		return "test";
	}
}
