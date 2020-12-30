package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.interfaces.RatingService;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

	@Mock
	private static RatingService ratingService;
	@Mock
	private BindingResult bindingResult;
	@Autowired
	private Rating rating;
	@InjectMocks
	private RatingController ratingController = new RatingController();

	@BeforeAll
	private static void init() {
	}

	@BeforeEach
	private void setUpPerTest() {
		rating = new Rating();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddRating() throws Exception {
		rating.setFitchRating("2.0");
		rating.setMoodysRating("3.5");
		rating.setOrderNumber(36);
		assertEquals("rating/add", ratingController.addRatingForm(rating));
	}

	@Test
	public void testRating() throws Exception {
		List<Rating> ratingReturned = new ArrayList<>();
		when(ratingService.getAllRating()).thenReturn(ratingReturned);
		final Model model = new ExtendedModelMap();
		assertEquals("rating/list", ratingController.home(model));
		assertNotNull(model.asMap().get("ratings"));
	}

	@Test
	public void testPostRating() throws Exception {
		rating.setFitchRating("2.0");
		rating.setMoodysRating("3.5");
		rating.setOrderNumber(36);
		List<Rating> ratingReturned = new ArrayList<>();
		ratingReturned.add(rating);
		when(ratingService.saveRatingrDb(any(Rating.class))).thenReturn(rating);
		when(ratingService.getAllRating()).thenReturn(ratingReturned);
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		ratingController.validate(rating, bindingResult, model);
		assertNotNull(model.asMap().get("ratings"));
		assertEquals("redirect:/rating/list", ratingController.validate(rating, bindingResult, model));
	}
	
	@Test
	public void testPostRatingError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		ratingController.validate(rating, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("rating"));
		assertEquals("rating/add", ratingController.validate(rating, bindingResult, model));
	}
	
	@Test
	public void testUpdateRating() throws Exception {
		rating.setFitchRating("2.0");
		rating.setMoodysRating("3.5");
		rating.setOrderNumber(36);
		when(ratingService.findRatingById(any())).thenReturn(rating);
		final Model model = new ExtendedModelMap();
		ratingController.showUpdateForm(1, model);
		assertNotNull(model.asMap().get("rating"));
		assertEquals("rating/update", ratingController.showUpdateForm(1, model));
	}
	
	@Test
	public void testPutRating() throws Exception {
		rating.setFitchRating("2.0");
		rating.setMoodysRating("3.5");
		rating.setOrderNumber(36);
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		ratingController.updateRating(1, rating, bindingResult, model);
		assertNotNull(model.asMap().get("ratings"));
		assertEquals("redirect:/rating/list", ratingController.updateRating(1, rating, bindingResult, model));
	}
	
	@Test
	public void testPUtRatingError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		ratingController.updateRating(1, rating, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("ratings"));
		assertEquals("rating/update", ratingController.updateRating(1, rating, bindingResult, model));
	}
	
	@Test
	public void testDeleteRating() throws Exception {
		rating.setFitchRating("2.0");
		rating.setMoodysRating("3.5");
		rating.setOrderNumber(36);
		final Model model = new ExtendedModelMap();
		ratingController.deleteRating(1, model);
		assertNotNull(model.asMap().get("ratings"));
		assertEquals("redirect:/rating/list", ratingController.deleteRating(1, model));
	}
}
