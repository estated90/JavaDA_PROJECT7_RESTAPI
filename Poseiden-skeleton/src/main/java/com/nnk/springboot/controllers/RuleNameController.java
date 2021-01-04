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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.interfaces.RuleNameService;

@Controller
public class RuleNameController {

	private static final Logger logger = LogManager.getLogger("RuleNameController");
	@Autowired
	private RuleNameService ruleNameService;
	private static final String RULENAMES = "ruleNames";
	private static final String REDIRECT = "redirect:/ruleName/list";

	@GetMapping("/ruleName/list")
	public String home(Model model) {
		logger.info("Getting all rule name of DB");
		model.addAttribute(RULENAMES, ruleNameService.getAllRuleName());
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		logger.info("Creation of the rule name : {}", ruleName);
		if (result.hasErrors()) {
			logger.error("rule name data was not valid : {}", ruleName);
			model.addAttribute("errors",result.getAllErrors());
			return "ruleName/add";
		}
		ruleNameService.saveRuleNameDb(ruleName);
		model.addAttribute(RULENAMES, ruleNameService.getAllRuleName());
		logger.info("{} has been created in the db", ruleName);
		return REDIRECT;
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting rule name with id : {}", id);
		RuleName ruleName = ruleNameService.findRuleNameById(id);
		model.addAttribute("ruleName", ruleName);
		logger.info("Returning rule name : {}", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		logger.info("Updating rule name : {} with id : {}", ruleName, id);
		if (result.hasErrors()) {
			logger.info("rule name was not valid : {} with id : {}", ruleName, id);
			model.addAttribute("errors",result.getAllErrors());
			return "ruleName/update";
		}
		ruleNameService.updateRuleName(id, ruleName);
		model.addAttribute(RULENAMES, ruleNameService.getAllRuleName());
		logger.info("rule name was udpated");
		return REDIRECT;
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting rule name with id : {}", id);
		ruleNameService.deletRuleName(id);
		model.addAttribute(RULENAMES, ruleNameService.getAllRuleName());
		logger.info("rule name was deleted");
		return REDIRECT;
	}
}
