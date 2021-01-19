package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.nnk.springboot.Application;
import com.nnk.springboot.configuration.SpringSecurityWebAuxTestConfig;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { Application.class,
		SpringSecurityWebAuxTestConfig.class })
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
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    void testHome() throws Exception {
	final Model model = new ExtendedModelMap();
	assertEquals("redirect:/bidList/list", homeController.home(model));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void testHomeAdmin() throws Exception {
	final Model model = new ExtendedModelMap();
	assertEquals("home", homeController.adminHome(model));
    }
}
