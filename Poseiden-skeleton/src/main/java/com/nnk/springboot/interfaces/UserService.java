package com.nnk.springboot.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.UserException;

/**
 * @author nicolas
 *
 */
@Service
public interface UserService {

    /**
     * @return List of all users
     */
    List<User> findAllUser();

	/**
	 * @param user The object to update from
	 * @return the object updated
     * @throws UserException Exception related to user not found
     */
    User saveUserDb(User user) throws UserException;

	/**
	 * @param id the object id to find
	 * @return Object with the Id provided
	 */
    User findById(Integer id) ;

	/**
	 * @param id the object id to find
	 * @param user The object to update from
	 * @return the object updated
     * @throws UserException Exception related to user not found
     */
    User updateUserId(Integer id, User user) throws UserException;
    
	/**
	 * @param id the object id to find
	 */
    void deleteUser(Integer id);

	/**
	 * @param userName of the user
	 * @return the object
	 */
	User findByUserName(String userName);
}