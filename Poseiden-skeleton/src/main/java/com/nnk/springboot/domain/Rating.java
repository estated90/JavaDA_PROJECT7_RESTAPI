package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id", columnDefinition = "TINYINT", nullable = false)
	private int id;
	@Column(name = "moodysRating", length = 125)
	private String moodysRating;
	@Column(name = "sandPRating", length = 125)
	private String sandPRating;
	@Column(name = "fitchRating", length = 125)
	private String fitchRating;
	@Column(name = "orderNumber", columnDefinition = "TINYINT")
	private int orderNumber;
	
	public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
	    this.moodysRating = moodysRating;
	    this.sandPRating = sandPRating;
	    this.fitchRating = fitchRating;
	    this.orderNumber = orderNumber;
	}
	/**
	 * @return the id
	 */
	public int getId() {
	    return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
	    this.id = id;
	}
	/**
	 * @return the moodysRating
	 */
	public String getMoodysRating() {
	    return moodysRating;
	}
	/**
	 * @param moodysRating the moodysRating to set
	 */
	public void setMoodysRating(String moodysRating) {
	    this.moodysRating = moodysRating;
	}
	/**
	 * @return the sandPRating
	 */
	public String getSandPRating() {
	    return sandPRating;
	}
	/**
	 * @param sandPRating the sandPRating to set
	 */
	public void setSandPRating(String sandPRating) {
	    this.sandPRating = sandPRating;
	}
	/**
	 * @return the fitchRating
	 */
	public String getFitchRating() {
	    return fitchRating;
	}
	/**
	 * @param fitchRating the fitchRating to set
	 */
	public void setFitchRating(String fitchRating) {
	    this.fitchRating = fitchRating;
	}
	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
	    return orderNumber;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
	    this.orderNumber = orderNumber;
	}
}
