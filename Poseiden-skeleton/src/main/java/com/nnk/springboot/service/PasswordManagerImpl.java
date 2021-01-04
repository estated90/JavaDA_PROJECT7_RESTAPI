package com.nnk.springboot.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	return passwordEncoder.encode(password);
    }

    @Override
    public boolean passwordDecoder(String password, String encodedPassword) {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	return passwordEncoder.matches(password, encodedPassword);
    }

    @Override
    public boolean isValidPassword(String password) {
	// Regex to check valid password.
	String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
	Pattern p = Pattern.compile(regex);
	if (password == null) {
	    return false;
	}
	Matcher m = p.matcher(password);
	return m.matches();
    }
}
