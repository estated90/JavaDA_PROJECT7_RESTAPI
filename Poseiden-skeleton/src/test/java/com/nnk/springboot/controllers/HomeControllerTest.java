package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController = new HomeController();

    @BeforeAll
    private static void init() {
    }

    @BeforeEach
    private void setUpPerTest() {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testHome() throws Exception {
	final Model model = new ExtendedModelMap();
	assertEquals("home", homeController.home(model));
    }

    @Test
    void testHomeAdmin() throws Exception {
	final Model model = new ExtendedModelMap();
	assertEquals("redirect:/bidList/list", homeController.adminHome(model));
    }
}
