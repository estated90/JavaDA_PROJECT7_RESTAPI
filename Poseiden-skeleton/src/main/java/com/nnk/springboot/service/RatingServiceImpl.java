package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.interfaces.RatingService;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	private static final Logger logger = LogManager.getLogger("RatingServiceImpl");
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Rating> getAllRating() {
		logger.info("Getting all the rating from db");
		List<Rating> rating = ratingRepository.findAll();
		logger.info("rating returned :{}", rating);
		return rating;
	}

	@Override
	@Transactional
	public Rating saveRatingrDb(Rating rating) {
		logger.info("Saving new rating:{}",rating);
		ratingRepository.save(rating);
		logger.info("rating created:{}",rating);
		return rating;
	}

	@Override
	@Transactional(readOnly=true)
	public Rating findRatingById(Integer id) {
		logger.info("Finding the rating with id :{}", id);
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		logger.info("Returning the rating with id :{} : {}", id, rating);
		return rating;
	}

	@Override
	@Transactional
	public Rating updateRating(Integer id, Rating rating) {
		logger.info("Updating the rating with id :{}", id);
		rating.setId(id);
		ratingRepository.save(rating);
		logger.info("The rating with id :{} was updated : {}", id, rating);
		return rating;
	}

	@Override
	@Transactional
	public void deletRating(Integer id) {
		logger.info("Deleting the rating with id :{}", id);
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		ratingRepository.delete(rating);
		logger.info("rating with id :{} was deleted", id);
	}
}
