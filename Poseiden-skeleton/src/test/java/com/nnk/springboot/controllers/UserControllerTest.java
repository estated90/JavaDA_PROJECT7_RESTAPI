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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.interfaces.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private static UserService userService;
	@Mock
	private BindingResult bindingResult;
	@Autowired
	private User user;
	@InjectMocks
	private UserController userController = new UserController();

	@BeforeAll
	private static void init() {
	}

	@BeforeEach
	private void setUpPerTest() {
		user = new User();
		user.setFullname("test");
		user.setUsername("test");
		user.setPassword("test");
		user.setRole("USER");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddUser() throws Exception {
		assertEquals("user/add", userController.addUser(user));
	}

	@Test
	public void testUser() throws Exception {
		List<User> userReturned = new ArrayList<>();
		when(userService.findAllUser()).thenReturn(userReturned);
		final Model model = new ExtendedModelMap();
		assertEquals("user/list", userController.home(model));
		assertNotNull(model.asMap().get("users"));
	}

	@Test
	public void testPostUser() throws Exception {
		List<User> userReturned = new ArrayList<>();
		userReturned.add(user);
		when(userService.saveUserDb(any(User.class))).thenReturn(user);
		when(userService.findAllUser()).thenReturn(userReturned);
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		userController.validate(user, bindingResult, model);
		assertNotNull(model.asMap().get("users"));
		assertEquals("redirect:/user/list", userController.validate(user, bindingResult, model));
	}

	@Test
	public void testPostUserError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		userController.validate(user, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("user"));
		assertEquals("user/add", userController.validate(user, bindingResult, model));
	}

	@Test
	public void testUpdateCurve() throws Exception {
		when(userService.findById(any())).thenReturn(user);
		final Model model = new ExtendedModelMap();
		userController.showUpdateForm(1, model);
		assertNotNull(model.asMap().get("user"));
		assertEquals("user/update", userController.showUpdateForm(1, model));
	}

	@Test
	public void testPutUser() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		userController.updateUser(1, user, bindingResult, model);
		assertNotNull(model.asMap().get("users"));
		assertEquals("redirect:/user/list", userController.updateUser(1, user, bindingResult, model));
	}

	@Test
	public void testPutUserError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		userController.updateUser(1, user, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("users"));
		assertEquals("user/update", userController.updateUser(1, user, bindingResult, model));
	}

	@Test
	public void testDeleteUser() throws Exception {
		final Model model = new ExtendedModelMap();
		userController.deleteUser(1, model);
		assertNotNull(model.asMap().get("users"));
		assertEquals("redirect:/user/list", userController.deleteUser(1, model));
	}
}
