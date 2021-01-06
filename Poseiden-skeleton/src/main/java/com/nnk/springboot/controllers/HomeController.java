package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>Controller allowing the redirection to the home page</p>
 * @author nicol
 *
 */
@Controller
public class HomeController {
	
	private String currentPrincipalName;
	private static final Logger logger = LogManager.getLogger("HomeController");
	
	/**
	 * @param model from thymeleaf
	 * @return link to redirect to bidlist page
	 */
	@GetMapping("/")
	public String home(Model model) {
		currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("User {} accessed home page", currentPrincipalName);
		return "redirect:/bidList/list";
	}

	
	/**
	 * @param model from thymeleaf
	 * @return link to redirect to home page
	 */
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Admin {} accessed home page", currentPrincipalName);
		return "home";
	}

}
