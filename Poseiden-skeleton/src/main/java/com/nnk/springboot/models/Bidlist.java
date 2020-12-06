package com.nnk.springboot.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository
@Table(name= "bidlist")
public class Bidlist {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BidListId", columnDefinition = "TINYINT", nullable = false)
	private int bidListId;
	
}
