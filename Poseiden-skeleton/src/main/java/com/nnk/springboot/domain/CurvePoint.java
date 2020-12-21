package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CurvePoint")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", columnDefinition = "TINYINT", nullable = false)
	private Integer id;
	@Column(name = "CurveId", columnDefinition = "TINYINT")
	private int curveId;
	@Column(name = "asOfDate")
	private LocalDateTime asOfDate;
	@Column(name = "term")
	private double term;
	@Column(name = "value")
	private double value;
	@Column(name = "creationDate")
	private LocalDateTime creationDate;

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
	 * @return the curveId
	 */
	public int getCurveId() {
		return curveId;
	}

	/**
	 * @param curveId the curveId to set
	 */
	public void setCurveId(int curveId) {
		this.curveId = curveId;
	}

	/**
	 * @return the asOfDate
	 */
	public LocalDateTime getAsOfDate() {
		return asOfDate;
	}

	/**
	 * @param asOfDate the asOfDate to set
	 */
	public void setAsOfDate(LocalDateTime asOfDate) {
		this.asOfDate = asOfDate;
	}

	/**
	 * @return the term
	 */
	public double getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(double term) {
		this.term = term;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
