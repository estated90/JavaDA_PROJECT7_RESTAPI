package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.InvalidUserException;
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
	public List<User> findAllUser() {
		logger.info("Getting all the User from db");
		return userRepository.findAll();
	}

	@Override
	public User saveUserDb(User user) {
		logger.info("Saving new user:{}", user);
		user.setPassword(passwordManager.passwordEncoder((user.getPassword())));
		userRepository.save(user);
		logger.info("User created:{}", user);
		return user;
	}

	@Override
	public User findById(Integer id) throws InvalidUserException {
		logger.info("Finding the user with id :{}", id);
		User user = userRepository.findById(id).orElseThrow(() -> new InvalidUserException("Invalid user Id:" + id));
		user.setPassword("");
		logger.info("Returning the user with id :{} : {}", id, user);
		return user;
	}

	@Override
	public User updateUserId(Integer id, User user) {
		logger.info("Updating the user with id :{}", id);
		user.setPassword(passwordManager.passwordEncoder((user.getPassword())));
		user.setId(id);
		userRepository.save(user);
		logger.info("The user with id :{} was updated : {}", id, user);
		return user;
	}

	@Override
	public void deleteUser(Integer id) {
		logger.info("Deleting the user with id :{}", id);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		logger.info("User with id :{} was deleted", id);
	}
}
