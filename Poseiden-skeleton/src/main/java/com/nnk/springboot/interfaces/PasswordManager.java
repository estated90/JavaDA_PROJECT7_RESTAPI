package com.nnk.springboot.interfaces;

/**
 * @author nicol
 *
 */
public interface PasswordManager {

	/**
	 * @param passwordToEncode password to encode then store in DB
	 * @return the encrypted password
	 */
	String passwordEncoder(String passwordToEncode);

	/**
	 * @param password sent by user
	 * @param encodedPassword encrypted in DB
	 * @return boolean to assess the given and stored are the same
	 */
	boolean passwordDecoder(String password, String encodedPassword);

	boolean isValidPassword(String password);

}