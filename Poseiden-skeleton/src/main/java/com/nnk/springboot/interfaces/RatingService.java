package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.Rating;

/**
 * @author nicolas
 *
 */
public interface RatingService {

    /**
     * @return List of all ratings
     */
	List<Rating> getAllRating();

	/**
	 * @param rating The object to update from
	 * @return the object updated
	 */
	Rating saveRatingrDb(Rating rating);

	/**
	 * @param id the object id to find
	 * @return Object with the Id provided
	 */
	Rating findRatingById(Integer id);

	/**
	 * @param id the object id to find
	 * @param rating The object to update from
	 * @return the object updated
	 */
	Rating updateRating(Integer id, Rating rating);

	/**
	 * @param id the object id to find
	 */
	void deletRating(Integer id);

}