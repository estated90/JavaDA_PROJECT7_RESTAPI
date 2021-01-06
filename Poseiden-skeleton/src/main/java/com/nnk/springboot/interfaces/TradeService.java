package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.Trade;

/**
 * @author nicolas
 *
 */
public interface TradeService {

    /**
     * @return List of all Trade
     */
	List<Trade> getAllTrade();

	/**
	 * @param trade The object to update from
	 * @return the object updated
	 */
	Trade saveTradeDb(Trade trade);

	/**
	 * @param id the object id to find
	 * @return Object with the Id provided
	 */
	Trade findTradeById(Integer id);

	/**
	 * @param id the object id to find
	 * @param trade The object to update from
	 * @return the object updated
	 */
	Trade updateTrade(Integer id, Trade trade);

	/**
	 * @param id the object id to find
	 */
	void deleteTrade(Integer id);

}