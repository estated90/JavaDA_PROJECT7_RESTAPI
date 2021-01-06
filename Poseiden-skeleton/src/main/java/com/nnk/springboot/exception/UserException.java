package com.nnk.springboot.exception;

/**
 * @author nicol
 *
 */
public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * @param message of the error
	 */
	public UserException(String message) {
		super(message);
	}
}
