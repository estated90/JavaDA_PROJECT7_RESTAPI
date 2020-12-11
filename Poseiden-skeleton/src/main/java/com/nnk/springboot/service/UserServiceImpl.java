package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUser(){
	return userRepository.findAll();
    }
    
}
