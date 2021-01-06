package com.nnk.springboot.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author nicol
 *
 */
public class UserUtils {

    private UserUtils () {
	throw new IllegalStateException("Utility class");
    }
    
    public static String getAuthenticatedUserName() {
        Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication();
        return auth != null ? ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername() : null;
    }
	
}
