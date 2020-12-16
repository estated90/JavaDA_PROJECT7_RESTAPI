package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.interfaces.TradeService;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeImpl implements TradeService {

	private static final Logger logger = LogManager.getLogger("TradeImpl");
	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public List<Trade> getAllTrade() {
		logger.info("Getting all the trade from db");
		List<Trade> trade = tradeRepository.findAll();
		logger.info("trade returned :{}", trade);
		return trade;
	}

	@Override
	public Trade saveTradeDb(Trade trade) {
		logger.info("Saving new trade:{}",trade);
		tradeRepository.save(trade);
		logger.info("trade created:{}",trade);
		return trade;
	}

	@Override
	public Trade findTradeById(Integer id) {
		logger.info("Finding the trade with id :{}", id);
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		logger.info("Returning the trade with id :{} : {}", id, trade);
		return trade;
	}

	@Override
	public Trade updateTrade(Integer id, Trade trade) {
		logger.info("Updating the trade with id :{}", id);
		trade.setTradeId(id);
		tradeRepository.save(trade);
		logger.info("The trade with id :{} was updated : {}", id, trade);
		return trade;
	}

	@Override
	public void deleteTrade(Integer id) {
		logger.info("Deleting the trade with id :{}", id);
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		tradeRepository.delete(trade);
		logger.info("trade with id :{} was deleted", id);
	}
}
