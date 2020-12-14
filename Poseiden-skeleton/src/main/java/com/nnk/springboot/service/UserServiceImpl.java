package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.InvalidUserException;
import com.nnk.springboot.interfaces.PasswordManager;
import com.nnk.springboot.interfaces.UserService;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordManager passwordManager;

    @Override
    public List<User> findAllUser() {
	return userRepository.findAll();
    }

    @Override
    public User saveUserDb(User user) {
	user.setPassword(passwordManager.passwordEncoder((user.getPassword())));
	userRepository.save(user);
	return user;
    }
    
    @Override 
    public User findById(Integer id) throws InvalidUserException {
	User user = userRepository.findById(id)
		.orElseThrow(() -> new InvalidUserException("Invalid user Id:" + id));
	user.setPassword("");
	return user;
    }
    
    @Override
    public User updateUserId(Integer id, User user) {
	user.setPassword(passwordManager.passwordEncoder((user.getPassword())));
	user.setId(id);
	userRepository.save(user);
	return user;
    }
    
    @Override
    public void deleteUser(Integer id) {
	User user = userRepository.findById(id)
		.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	userRepository.delete(user);
    }
}
