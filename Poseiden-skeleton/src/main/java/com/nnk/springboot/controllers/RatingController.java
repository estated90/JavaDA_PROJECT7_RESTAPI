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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.interfaces.RatingService;

/**
 * @author nicolas
 * <p>Controller CRUD to access the Rating data</p>
 */
@Controller
public class RatingController {

	private static final Logger logger = LogManager.getLogger("RatingController");
	@Autowired
	private RatingService ratingService;
	private static final String RATINGS = "ratings";
	private static final String REDIRECT = "redirect:/rating/list";

	/**
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/rating/list")
	public String home(Model model) {
		logger.info("Getting all rating of DB");
		model.addAttribute(RATINGS, ratingService.getAllRating());
		return "rating/list";
	}

	/**
	 * @param bid as an object
	 * @return the link of page
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	/**
	 * @param bid as an object
	 * @param result control validity object
	 * @param model to add from
	 * @return the link of page
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		logger.info("Creation of the rating : {}", rating);
		if (result.hasErrors()) {
			logger.error("rating data was not valid : {}", rating);
			model.addAttribute("errors",result.getAllErrors());
			return "rating/add";
		}
		ratingService.saveRatingrDb(rating);
		model.addAttribute(RATINGS, ratingService.getAllRating());
		logger.info("{} has been created in the db", rating);
		return REDIRECT;
	}

	/**
	 * @param id of the bid as int
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting rating with id : {}", id);
		Rating rating = ratingService.findRatingById(id);
		model.addAttribute("rating", rating);
		logger.info("Returning rating : {}", rating);
		return "rating/update";
	}

	/**
	 * @param id of the bid as int
	 * @param bidList as an object
	 * @param result control validity object
	 * @param model to add from
	 * @return the link of page
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		logger.info("Updating rating : {} with id : {}", rating, id);
		if (result.hasErrors()) {
			logger.info("rating was not valid : {} with id : {}", rating, id);
			model.addAttribute("errors",result.getAllErrors());
			return "rating/update";
		}
		ratingService.updateRating(id, rating);
		model.addAttribute(RATINGS, ratingService.getAllRating());
		logger.info("rating was udpated");
		return REDIRECT;
	}

	/**
	 * @param id of the bid as int
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting rating with id : {}", id);
		ratingService.deletRating(id);
		model.addAttribute(RATINGS, ratingService.getAllRating());
		logger.info("rating was deleted");
		return REDIRECT;
	}
}
