package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

/**
 * @author nicol
 *
 */
@Entity
@Table(name = "Rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", columnDefinition = "TINYINT", nullable = false)
	private Integer id;
	@Column(name = "moodysRating", length = 125)
	private String moodysRating;
	@Column(name = "sandPRating", length = 125)
	private String sandPRating;
	@Column(name = "fitchRating", length = 125)
	private String fitchRating;
	@Column(name = "orderNumber")
	@Positive
	private int orderNumber;

	public Rating() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
