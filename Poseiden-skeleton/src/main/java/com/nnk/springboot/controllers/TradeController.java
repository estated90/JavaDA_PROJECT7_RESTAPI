package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.interfaces.TradeService;

@Controller
public class TradeController {

	private static final Logger logger = LogManager.getLogger("TradeController");
	@Autowired
	private TradeService tradeService;
	private static final String TRADES = "trades";
	private static final String REDIRECT = "redirect:/trade/list";

	@GetMapping("/trade/list")
	public String home(Model model) {
		logger.info("Getting all trade of DB");
		model.addAttribute(TRADES, tradeService.getAllTrade());
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addTrade(Trade bid) {
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		logger.info("Creation of the trade: {}", trade);
		if (result.hasErrors()) {
			logger.error("trade data was not valid : {}", trade);
			model.addAttribute("errors",result.getAllErrors());
			return "trade/add";
		}
		tradeService.saveTradeDb(trade);
		model.addAttribute(TRADES, tradeService.getAllTrade());
		logger.info("{} has been created in the db", trade);
		return REDIRECT;
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting trade with id : {}", id);
		Trade trade = tradeService.findTradeById(id);
		model.addAttribute("trade", trade);
		logger.info("Returning trade : {}", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		logger.info("Updating trade : {} with id : {}", trade, id);
		if (result.hasErrors()) {
			logger.info("trade was not valid : {} with id : {}", trade, id);
			model.addAttribute("errors",result.getAllErrors());
			return "trade/update";
		}
		tradeService.updateTrade(id, trade);
		model.addAttribute(TRADES, tradeService.getAllTrade());
		logger.info("trade was udpated");
		return REDIRECT;
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting trade with id : {}", id);
		tradeService.deleteTrade(id);
		model.addAttribute(TRADES, tradeService.getAllTrade());
		logger.info("trade was deleted");
		return REDIRECT;
	}
}
