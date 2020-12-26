package com.nnk.springboot.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.UserUtils;
import com.nnk.springboot.service.UserCache;

@Service
public class CustomLogoutHandler implements LogoutHandler{

    private final UserCache userCache;

    public CustomLogoutHandler(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String userName = UserUtils.getAuthenticatedUserName();
        userCache.evictUser(userName);
    }
	
}
