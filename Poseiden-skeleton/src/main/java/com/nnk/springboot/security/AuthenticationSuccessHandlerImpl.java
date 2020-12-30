package com.nnk.springboot.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nnk.springboot.repositories.UserRepository;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		GrantedAuthority authority = authentication.getAuthorities().stream()
				.filter(a -> a.getAuthority().equals("USER")).findAny().orElse(null);
		// Very simple (most probably broken) check if the user is ADMIN or USER
		if (authority != null) {
			redirectStrategy.sendRedirect(request, response, "/bidList/list");
		} else {
			redirectStrategy.sendRedirect(request, response, "user/list");
		}
	}

}
