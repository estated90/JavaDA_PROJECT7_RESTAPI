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
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;

@Controller
public class BidListController {
	
	private static final Logger logger = LogManager.getLogger("BidListController");
	@Autowired
	private BidListService bidListService;

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		logger.info("Getting all bids of DB");
		model.addAttribute("bidlist", bidListService.getAllBidList());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("Creation of the bids : {}", bid);
		if (result.hasErrors()) {
			logger.error("bids data was not valid : {}", bid);
			return "bidList/add";
		}
		bidListService.saveBidListDb(bid);
		model.addAttribute("bidlist", bidListService.getAllBidList());
		logger.info("{} has been created in the db", bid);
		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting bids with id : {}", id);
		BidList bid = bidListService.findById(id);
		model.addAttribute("bidlist", bid);
		logger.info("Returning bids : {}", bid);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		logger.info("Updating bids : {} with id : {}", bidList, id);
		if (result.hasErrors()) {
			logger.info("bids was not valid : {} with id : {}", bidList, id);
			return "bidList/update";
		}
		bidListService.updateBidList(id, bidList);
		model.addAttribute("bidlist", bidListService.findById(id));
		logger.info("bids was udpated");
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting bids with id : {}", id);
		bidListService.deletBidList(id);
		model.addAttribute("bidlist", bidListService.getAllBidList());
		logger.info("bids was deleted");
		return "redirect:/bidList/list";
	}
}
