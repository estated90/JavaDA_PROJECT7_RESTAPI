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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.interfaces.RatingService;

@Controller
public class RatingController {

	private static final Logger logger = LogManager.getLogger("RatingController");
	@Autowired
	private RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		logger.info("Getting all rating of DB");
		model.addAttribute("ratings", ratingService.getAllRating());
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		logger.info("Creation of the rating : {}", rating);
		if (result.hasErrors()) {
			logger.error("rating data was not valid : {}", rating);
			model.addAttribute("errors",result.getAllErrors());
			return "rating/add";
		}
		ratingService.saveRatingrDb(rating);
		model.addAttribute("ratings", ratingService.getAllRating());
		logger.info("{} has been created in the db", rating);
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting rating with id : {}", id);
		Rating rating = ratingService.findRatingById(id);
		model.addAttribute("rating", rating);
		logger.info("Returning rating : {}", rating);
		return "rating/update";
	}

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
		model.addAttribute("ratings", ratingService.getAllRating());
		logger.info("rating was udpated");
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting rating with id : {}", id);
		ratingService.deletRating(id);
		model.addAttribute("ratings", ratingService.getAllRating());
		logger.info("rating was deleted");
		return "redirect:/rating/list";
	}
}
