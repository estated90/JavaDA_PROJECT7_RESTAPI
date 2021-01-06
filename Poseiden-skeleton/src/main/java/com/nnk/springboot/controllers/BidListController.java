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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;

/**
 * @author nicolas
 *<p>Controller CRUD to access the Bid data</p>
 */
@Controller
public class BidListController {
	
	private static final Logger logger = LogManager.getLogger("BidListController");
	@Autowired
	
	private BidListService bidListService;
	/**
	 * <p>Parameter of the model used by thymeleaf for front display</p>
	 */
	private static final String BIDLISTS = "bidlists";
	/**
	 * <p>Redirection link</p>
	 */
	private static final String REDIRECT = "redirect:/bidList/list";
	
	/**
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/bidList/list")
	public String home(Model model) {
		logger.info("Getting all bids of DB");
		model.addAttribute(BIDLISTS, bidListService.getAllBidList());
		return "bidList/list";
	}

	/**
	 * @param bid as an object
	 * @return the link of page
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	/**
	 * @param bid as an object
	 * @param result control validity object
	 * @param model to add from
	 * @return the link of page
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("Creation of the bids : {}", bid);
		if (result.hasErrors()) {
			logger.error("bids data was not valid : {}", bid);
			model.addAttribute("errors",result.getAllErrors());
			return "bidList/add";
		}
		bidListService.saveBidListDb(bid);
		model.addAttribute("bidlist", bidListService.getAllBidList());
		logger.info("{} has been created in the db", bid);
		return REDIRECT;
	}

	/**
	 * @param id of the bid as int
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting bids with id : {}", id);
		BidList bid = bidListService.findById(id);
		model.addAttribute("bidList", bid);
		logger.info("Returning bids : {}", bid);
		return "bidList/update";
	}

	/**
	 * @param id of the bid as int
	 * @param bidList as an object
	 * @param result control validity object
	 * @param model to add from
	 * @return the link of page
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		logger.info("Updating bids : {} with id : {}", bidList, id);
		if (result.hasErrors()) {
			logger.info("bids was not valid : {} with id : {}", bidList, id);
			model.addAttribute("errors",result.getAllErrors());
			return "bidList/update";
		}
		bidListService.updateBidList(id, bidList);
		model.addAttribute(BIDLISTS, bidListService.findById(id));
		logger.info("bids was udpated");
		return REDIRECT;
	}

	/**
	 * @param id of the bid as int
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting bids with id : {}", id);
		bidListService.deletBidList(id);
		model.addAttribute(BIDLISTS, bidListService.getAllBidList());
		logger.info("bids was deleted");
		return REDIRECT;
	}
}
