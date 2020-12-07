package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", columnDefinition = "TINYINT", nullable = false)
    private int id;
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

    public CurvePoint(int curveId, double term, double value) {
	this.curveId = curveId;
	this.term = term;
	this.value = value;
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
