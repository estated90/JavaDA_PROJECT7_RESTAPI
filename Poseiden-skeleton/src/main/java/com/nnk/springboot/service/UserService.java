package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.InvalidUserException;

@Service
public interface UserService {

    List<User> findAllUser();

    User saveUserDb(User user);

    User findById(Integer id) throws InvalidUserException;

    User updateUserId(Integer id, User user);
    
    void deleteUser(Integer id);
}