package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.interfaces.PasswordManager;
import com.nnk.springboot.interfaces.UserService;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger("UserServiceImpl");
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordManager passwordManager;

	@Override
	@Transactional(readOnly=true)
	public List<User> findAllUser() {
		logger.info("Getting all the User from db");
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User saveUserDb(User user) {
		logger.info("Saving new user:{}", user);
		user.setPassword(passwordManager.passwordEncoder((user.getPassword())));
		userRepository.save(user);
		logger.info("User created:{}", user);
		return user;
	}

	@Override
	@Transactional(readOnly=true)
	public User findById(Integer id)  {
		logger.info("Finding the user with id :{}", id);
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		logger.info("Returning the user with id :{} : {}", id, user);
		return user;
	}

	@Override
	@Transactional
	public User updateUserId(Integer id, User user) {
		logger.info("Updating the user with id :{}", id);
		user.setPassword(passwordManager.passwordEncoder((user.getPassword())));
		user.setId(id);
		userRepository.save(user);
		logger.info("The user with id :{} was updated : {}", id, user);
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(Integer id) {
		logger.info("Deleting the user with id :{}", id);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		logger.info("User with id :{} was deleted", id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public User findByUserName(String userName)  {
		logger.info("Finding the user with user name :{}", userName);
		User user = userRepository.findByUsername(userName);
		user.setPassword("");
		logger.info("Returning the user with user name :{} : {}", userName, user);
		return user;
	}
}
