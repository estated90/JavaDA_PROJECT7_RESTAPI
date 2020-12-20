package com.nnk.springboot.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;

@Service
public interface UserService {

    List<User> findAllUser();

    User saveUserDb(User user);

    User findById(Integer id) ;

    User updateUserId(Integer id, User user);
    
    void deleteUser(Integer id);

	User findByUserName(String userName);
}