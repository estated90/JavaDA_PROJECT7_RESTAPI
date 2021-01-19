package com.nnk.springboot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

/**
 * @author nicol
 *
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Username is mandatory")
	@Column(name = "username", unique = true)
	private String username;
	@NotBlank(message = "Password must have at least 8 characters, 1 Upper and lower case and 1 special character")
	private String password;
	@NotBlank(message = "FullName is mandatory")
	private String fullname;
	@NotBlank(message = "Role is mandatory")
	private String role;
	@ColumnDefault("true")
	private boolean enabled;

	/**
	 * @param username of the user
	 * @param password created by user
	 * @param fullname of the user
	 * @param role of the user to access data
	 */

	public User(String username, String password, String fullname, String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
