package com.nnk.springboot.interfaces;

/**
 * @author nicolas
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

	/**
	 * @param password without encryption
	 * @return boolean to valid if the password has at least 8 characters, 1 upper case, 1 lower case and 1 special character
	 */
	boolean isValidPassword(String password);

}