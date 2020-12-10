package com.nnk.springboot.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    private User user;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	user = userService.findUserByUserName(userName);
	List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
	return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(String string) {
	Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
	roles.add(new SimpleGrantedAuthority(user.getRole()));
	List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
               true, true, true, true, authorities);
    }

}
