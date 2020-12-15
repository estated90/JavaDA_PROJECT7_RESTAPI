package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BidList")
public class BidList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BidListId", columnDefinition = "TINYINT")
	private Integer bidListId;
	@Column(name = "account", length = 30, nullable = false)
	private String account;
	@Column(name = "type", length = 30, nullable = false)
	private String type;
	@Column(name = "bidQuantity")
	private double bidQuantity;
	@Column(name = "askQuantity")
	private double askQuantity;
	@Column(name = "bid")
	private double bid;
	@Column(name = "ask")
	private double ask;
	@Column(name = "benchmark", length = 125)
	private String benchmark;
	@Column(name = "bidListDate")
	private LocalDateTime bidListDate;
	@Column(name = "commentary", length = 125)
	private String commentary;
	@Column(name = "security", length = 125)
	private String security;
	@Column(name = "status", length = 10)
	private String status;
	@Column(name = "trader", length = 125)
	private String trader;
	@Column(name = "book", length = 125)
	private String book;
	@Column(name = "creationName", length = 125)
	private String creationName;
	@Column(name = "creationDate")
	private LocalDateTime creationDate;
	@Column(name = "revisionName", length = 125)
	private String revisionName;
	@Column(name = "revisionDate")
	private LocalDateTime revisionDate;
	@Column(name = "dealName", length = 125)
	private String dealName;
	@Column(name = "dealType", length = 125)
	private String dealType;
	@Column(name = "sourceListId", length = 125)
	private String sourceListId;
	@Column(name = "side", length = 125)
	private String side;
	
	public BidList(String account, String type, double bidQuantity) {
	    this.bidQuantity=bidQuantity;
	    this.dealType = type;
	    this.account = account;
	}
	/**
	 * @return the bidListId
	 */
	public int getBidListId() {
	    return bidListId;
	}
	/**
	 * @param bidListId the bidListId to set
	 */
	public void setBidListId(int bidListId) {
	    this.bidListId = bidListId;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
	    return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
	    this.account = account;
	}
	/**
	 * @return the type
	 */
	public String getType() {
	    return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
	    this.type = type;
	}
	/**
	 * @return the bidQuantity
	 */
	public double getBidQuantity() {
	    return bidQuantity;
	}
	/**
	 * @param bidQuantity the bidQuantity to set
	 */
	public void setBidQuantity(double bidQuantity) {
	    this.bidQuantity = bidQuantity;
	}
	/**
	 * @return the askQuantity
	 */
	public double getAskQuantity() {
	    return askQuantity;
	}
	/**
	 * @param askQuantity the askQuantity to set
	 */
	public void setAskQuantity(double askQuantity) {
	    this.askQuantity = askQuantity;
	}
	/**
	 * @return the bid
	 */
	public double getBid() {
	    return bid;
	}
	/**
	 * @param bid the bid to set
	 */
	public void setBid(double bid) {
	    this.bid = bid;
	}
	/**
	 * @return the ask
	 */
	public double getAsk() {
	    return ask;
	}
	/**
	 * @param ask the ask to set
	 */
	public void setAsk(double ask) {
	    this.ask = ask;
	}
	/**
	 * @return the benchmark
	 */
	public String getBenchmark() {
	    return benchmark;
	}
	/**
	 * @param benchmark the benchmark to set
	 */
	public void setBenchmark(String benchmark) {
	    this.benchmark = benchmark;
	}
	/**
	 * @return the bidListDate
	 */
	public LocalDateTime getBidListDate() {
	    return bidListDate;
	}
	/**
	 * @param bidListDate the bidListDate to set
	 */
	public void setBidListDate(LocalDateTime bidListDate) {
	    this.bidListDate = bidListDate;
	}
	/**
	 * @return the commentary
	 */
	public String getCommentary() {
	    return commentary;
	}
	/**
	 * @param commentary the commentary to set
	 */
	public void setCommentary(String commentary) {
	    this.commentary = commentary;
	}
	/**
	 * @return the security
	 */
	public String getSecurity() {
	    return security;
	}
	/**
	 * @param security the security to set
	 */
	public void setSecurity(String security) {
	    this.security = security;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
	    return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
	    this.status = status;
	}
	/**
	 * @return the trader
	 */
	public String getTrader() {
	    return trader;
	}
	/**
	 * @param trader the trader to set
	 */
	public void setTrader(String trader) {
	    this.trader = trader;
	}
	/**
	 * @return the book
	 */
	public String getBook() {
	    return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(String book) {
	    this.book = book;
	}
	/**
	 * @return the creationName
	 */
	public String getCreationName() {
	    return creationName;
	}
	/**
	 * @param creationName the creationName to set
	 */
	public void setCreationName(String creationName) {
	    this.creationName = creationName;
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
	/**
	 * @return the revisionName
	 */
	public String getRevisionName() {
	    return revisionName;
	}
	/**
	 * @param revisionName the revisionName to set
	 */
	public void setRevisionName(String revisionName) {
	    this.revisionName = revisionName;
	}
	/**
	 * @return the revisionDate
	 */
	public LocalDateTime getRevisionDate() {
	    return revisionDate;
	}
	/**
	 * @param revisionDate the revisionDate to set
	 */
	public void setRevisionDate(LocalDateTime revisionDate) {
	    this.revisionDate = revisionDate;
	}
	/**
	 * @return the dealName
	 */
	public String getDealName() {
	    return dealName;
	}
	/**
	 * @param dealName the dealName to set
	 */
	public void setDealName(String dealName) {
	    this.dealName = dealName;
	}
	/**
	 * @return the dealType
	 */
	public String getDealType() {
	    return dealType;
	}
	/**
	 * @param dealType the dealType to set
	 */
	public void setDealType(String dealType) {
	    this.dealType = dealType;
	}
	/**
	 * @return the sourceListId
	 */
	public String getSourceListId() {
	    return sourceListId;
	}
	/**
	 * @param sourceListId the sourceListId to set
	 */
	public void setSourceListId(String sourceListId) {
	    this.sourceListId = sourceListId;
	}
	/**
	 * @return the side
	 */
	public String getSide() {
	    return side;
	}
	/**
	 * @param side the side to set
	 */
	public void setSide(String side) {
	    this.side = side;
	}
}
