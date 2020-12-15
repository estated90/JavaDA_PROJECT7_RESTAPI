package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {

	List<Trade> getAllTrade();

	Trade saveTradeDb(Trade trade);

	Trade findTradeById(Integer id);

	Trade updateTrade(Integer id, Trade trade);

	void deleteTrade(Integer id);

}