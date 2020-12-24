package com.nnk.springboot.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.UserException;

@Service
public interface UserService {

    List<User> findAllUser();

    User saveUserDb(User user) throws UserException;

    User findById(Integer id) ;

    User updateUserId(Integer id, User user) throws UserException;
    
    void deleteUser(Integer id);

	User findByUserName(String userName);
}