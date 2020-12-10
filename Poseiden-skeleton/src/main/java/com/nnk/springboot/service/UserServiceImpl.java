package com.nnk.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
	this.userRepository = userRepository;
	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUserName(String userName) {
	return userRepository.findByUsername(userName);
    }

    public User saveUser(User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	user.setFullname(user.getFullname());
	user.setRole(user.getRole());
	user.setUsername(user.getUsername());
	return userRepository.save(user);
    }
}
