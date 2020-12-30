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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.interfaces.RuleNameService;

@ExtendWith(MockitoExtension.class)
class RuleNameControllerTest {

	@Mock
	private static RuleNameService ruleService;
	@Mock
	private BindingResult bindingResult;
	@Autowired
	private RuleName ruleName;
	@InjectMocks
	private RuleNameController ruleController = new RuleNameController();

	@BeforeAll
	private static void init() {
	}

	@BeforeEach
	private void setUpPerTest() {
		ruleName = new RuleName();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddRating() throws Exception {
		ruleName.setDescription("description");
		ruleName.setJson("json as test");
		ruleName.setName("The Name");
		assertEquals("ruleName/add", ruleController.addRuleForm(ruleName));
	}

	@Test
	public void testRating() throws Exception {
		List<RuleName> ruleReturned = new ArrayList<>();
		when(ruleService.getAllRuleName()).thenReturn(ruleReturned);
		final Model model = new ExtendedModelMap();
		assertEquals("ruleName/list", ruleController.home(model));
		assertNotNull(model.asMap().get("ruleNames"));
	}

	@Test
	public void testPostRating() throws Exception {
		ruleName.setDescription("description");
		ruleName.setJson("json as test");
		ruleName.setName("The Name");
		List<RuleName> ruleReturned = new ArrayList<>();
		ruleReturned.add(ruleName);
		when(ruleService.saveRuleNameDb(any(RuleName.class))).thenReturn(ruleName);
		when(ruleService.getAllRuleName()).thenReturn(ruleReturned);
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		ruleController.validate(ruleName, bindingResult, model);
		assertNotNull(model.asMap().get("ruleNames"));
		assertEquals("redirect:/ruleName/list", ruleController.validate(ruleName, bindingResult, model));
	}
	
	@Test
	public void testPostRatingError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		ruleController.validate(ruleName, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("ruleName"));
		assertEquals("ruleName/add", ruleController.validate(ruleName, bindingResult, model));
	}
	
	@Test
	public void testUpdateRating() throws Exception {
		ruleName.setDescription("description");
		ruleName.setJson("json as test");
		ruleName.setName("The Name");
		when(ruleService.findRuleNameById(any())).thenReturn(ruleName);
		final Model model = new ExtendedModelMap();
		ruleController.showUpdateForm(1, model);
		assertNotNull(model.asMap().get("ruleName"));
		assertEquals("ruleName/update", ruleController.showUpdateForm(1, model));
	}
	
	@Test
	public void testPutRating() throws Exception {
		ruleName.setDescription("description");
		ruleName.setJson("json as test");
		ruleName.setName("The Name");
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		ruleController.updateRuleName(1, ruleName, bindingResult, model);
		assertNotNull(model.asMap().get("ruleNames"));
		assertEquals("redirect:/ruleName/list", ruleController.updateRuleName(1, ruleName, bindingResult, model));
	}
	
	@Test
	public void testPUtRatingError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		ruleController.updateRuleName(1, ruleName, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("ruleNames"));
		assertEquals("ruleName/update", ruleController.updateRuleName(1, ruleName, bindingResult, model));
	}
	
	@Test
	public void testDeleteRating() throws Exception {
		ruleName.setDescription("description");
		ruleName.setJson("json as test");
		ruleName.setName("The Name");
		final Model model = new ExtendedModelMap();
		ruleController.deleteRuleName(1, model);
		assertNotNull(model.asMap().get("ruleNames"));
		assertEquals("redirect:/ruleName/list", ruleController.deleteRuleName(1, model));
	}
}
