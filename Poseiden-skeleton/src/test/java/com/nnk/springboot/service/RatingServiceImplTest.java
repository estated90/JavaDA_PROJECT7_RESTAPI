package com.nnk.springboot.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.interfaces.RatingService;
import com.nnk.springboot.repositories.RatingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class RatingServiceImplTest {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingService ratingService;

    @Test
    @Order(1)
    @DisplayName("Saving and deleting a user")
    void testSavingUserDb() {
	Rating rating = new Rating();
	rating.setMoodysRating("test1");
	rating.setSandPRating("test1");
	rating.setFitchRating("test1");
	rating.setOrderNumber(10);
	ratingService.saveRatingrDb(rating);
	List<Rating> ratingAll = ratingService.getAllRating();
	assertEquals(1, ratingAll.size());
	assertNotNull(ratingAll.get(0).getId());
	assertEquals(rating.getMoodysRating(), ratingAll.get(0).getMoodysRating());
	assertEquals(rating.getSandPRating(), ratingAll.get(0).getSandPRating());
	assertEquals(rating.getFitchRating(), ratingAll.get(0).getFitchRating());
	assertEquals(rating.getOrderNumber(), ratingAll.get(0).getOrderNumber());
	int ratingId = ratingAll.get(0).getId();
	ratingService.deletRating(ratingId);;
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    ratingService.findRatingById(ratingId);
	    ;
	});
	String expectedMessage = "Invalid rating Id:" + ratingId;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(2)
    @DisplayName("Saving and updating a user")
    void testUpdatingUserDb() {
	Rating rating = new Rating();
	rating.setMoodysRating("test1");
	rating.setSandPRating("test1");
	rating.setFitchRating("test1");
	rating.setOrderNumber(10);
	ratingService.saveRatingrDb(rating);
	rating.setMoodysRating("test2");
	rating.setSandPRating("test2");
	int ratingId = ratingRepository.findAll().get(0).getId();
	ratingService.updateRating(ratingId, rating);
	Rating ratingUpdated = ratingService.findRatingById(ratingId);
	assertEquals(rating.getMoodysRating(), ratingUpdated.getMoodysRating());
	assertEquals(rating.getSandPRating(), ratingUpdated.getSandPRating());
	ratingService.deletRating(ratingId);
    }

    @Test
    @Order(3)
    @DisplayName("Deleting but failed")
    void testDeleteFail() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    ratingService.deletRating(10);
	    ;
	});
	String expectedMessage = "Invalid rating Id:" + 10;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

}
