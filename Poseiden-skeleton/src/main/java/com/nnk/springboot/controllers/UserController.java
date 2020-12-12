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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.InvalidUserException;
import com.nnk.springboot.service.UserService;

@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger("UserController");
    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public String home(Model model) {
	logger.info("Getting all user of DB");
	model.addAttribute("users", userService.findAllUser());
	return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
	return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
	logger.info("Creation of the user : {}", user);
	if (result.hasErrors()) {
	    logger.error("User data was not valid : {}", user);
	    return "user/add";
	}
	userService.saveUserDb(user);
	model.addAttribute("users", userService.findAllUser());
	logger.info("{} has been created in the db", user);
	return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws InvalidUserException {
	logger.info("Getting user with id : {}", id);
	User user = userService.findById(id);
	model.addAttribute("user", user);
	logger.info("Returning User : {}", user);
	return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
	logger.info("Updating user : {} with id : {}", user, id);
	if (result.hasErrors()) {
	    logger.info("User was not valid : {} with id : {}", user, id);
	    return "user/update";
	}
	userService.updateUserId(id, user);
	model.addAttribute("users", userService.findAllUser());
	logger.info("User was udpated");
	return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
	logger.info("Deleting user with id : {}", id);
	userService.deleteUser(id);
	model.addAttribute("users", userService.findAllUser());
	logger.info("User was deleted");
	return "redirect:/user/list";
    }
}
