package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;

@Service
public interface UserService {

    List<User> findAllUser();
    
}