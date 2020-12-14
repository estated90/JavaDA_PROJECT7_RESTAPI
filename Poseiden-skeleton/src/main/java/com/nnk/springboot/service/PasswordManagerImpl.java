package com.nnk.springboot.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.interfaces.PasswordManager;

@Service
public class PasswordManagerImpl implements PasswordManager {
	
	/**
	 *
	 */
	@Override
	public String passwordEncoder(String passwordToEncode) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordToEncode;
		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;
	}
	
	@Override
	public boolean passwordDecoder(String password, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, encodedPassword);
	}
	
}
