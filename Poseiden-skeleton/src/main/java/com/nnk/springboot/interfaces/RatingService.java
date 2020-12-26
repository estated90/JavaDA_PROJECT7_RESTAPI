package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface RatingService {

	List<Rating> getAllRating();

	Rating saveRatingrDb(Rating rating);

	Rating findRatingById(Integer id);

	Rating updateRating(Integer id, Rating rating);

	void deletRating(Integer id);

}