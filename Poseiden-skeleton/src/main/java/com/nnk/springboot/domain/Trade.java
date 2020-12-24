package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TradeId", columnDefinition = "TINYINT", nullable = false)
    private Integer tradeId;
    @Column(name = "account", length = 30, nullable = false)
    @NotBlank(message = "Account is mandatory")
    private String account;
    @Column(name = "type", length = 30, nullable = false)
    @NotBlank(message = "Type is mandatory")
    private String type;
    @Column(name = "buyQuantity")
    private double buyQuantity;
    @Column(name = "sellQuantity")
    private double sellQuantity;
    @Column(name = "buyPrice")
    private double buyPrice;
    @Column(name = "sellPrice")
    private double sellPrice;
    @Column(name = "tradeDate")
    private LocalDateTime tradeDate;
    @Column(name = "security", length = 125)
    private String security;
    @Column(name = "status", length = 10)
    private String status;
    @Column(name = "trader", length = 125)
    private String trader;
    @Column(name = "benchmark", length = 125)
    private String benchmark;
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

    public Trade() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @return the tradeId
     */
    public Integer getTradeId() {
	return tradeId;
    }

    /**
     * @param tradeId the tradeId to set
     */
    public void setTradeId(Integer tradeId) {
	this.tradeId = tradeId;
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
     * @return the buyQuantity
     */
    public double getBuyQuantity() {
	return buyQuantity;
    }

    /**
     * @param buyQuantity the buyQuantity to set
     */
    public void setBuyQuantity(double buyQuantity) {
	this.buyQuantity = buyQuantity;
    }

    /**
     * @return the sellQuantity
     */
    public double getSellQuantity() {
	return sellQuantity;
    }

    /**
     * @param sellQuantity the sellQuantity to set
     */
    public void setSellQuantity(double sellQuantity) {
	this.sellQuantity = sellQuantity;
    }

    /**
     * @return the buyPrice
     */
    public double getBuyPrice() {
	return buyPrice;
    }

    /**
     * @param buyPrice the buyPrice to set
     */
    public void setBuyPrice(double buyPrice) {
	this.buyPrice = buyPrice;
    }

    /**
     * @return the sellPrice
     */
    public double getSellPrice() {
	return sellPrice;
    }

    /**
     * @param sellPrice the sellPrice to set
     */
    public void setSellPrice(double sellPrice) {
	this.sellPrice = sellPrice;
    }

    /**
     * @return the tradeDate
     */
    public LocalDateTime getTradeDate() {
	return tradeDate;
    }

    /**
     * @param tradeDate the tradeDate to set
     */
    public void setTradeDate(LocalDateTime tradeDate) {
	this.tradeDate = tradeDate;
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
