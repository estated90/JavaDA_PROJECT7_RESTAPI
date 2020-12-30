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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.interfaces.TradeService;

@ExtendWith(MockitoExtension.class)
class TradeControllerTest {

	@Mock
	private static TradeService tradeService;
	@Mock
	private BindingResult bindingResult;
	@Autowired
	private Trade trade;
	@InjectMocks
	private TradeController tradeController = new TradeController();

	@BeforeAll
	private static void init() {
	}

	@BeforeEach
	private void setUpPerTest() {
		trade = new Trade();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddTrade() throws Exception {
		trade.setAccount("test");
		trade.setType("type test");
		trade.setBuyQuantity(20);
		assertEquals("trade/add", tradeController.addTrade(trade));
	}

	@Test
	public void testTrade() throws Exception {
		List<Trade> tradeReturned = new ArrayList<>();
		when(tradeService.getAllTrade()).thenReturn(tradeReturned);
		final Model model = new ExtendedModelMap();
		assertEquals("trade/list", tradeController.home(model));
		assertNotNull(model.asMap().get("trades"));
	}

	@Test
	public void testPostTrade() throws Exception {
		trade.setAccount("test");
		trade.setType("type test");
		trade.setBuyQuantity(20);
		List<Trade> tradeReturned = new ArrayList<>();
		tradeReturned.add(trade);
		when(tradeService.saveTradeDb(any(Trade.class))).thenReturn(trade);
		when(tradeService.getAllTrade()).thenReturn(tradeReturned);
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		tradeController.validate(trade, bindingResult, model);
		assertNotNull(model.asMap().get("trades"));
		assertEquals("redirect:/trade/list", tradeController.validate(trade, bindingResult, model));
	}

	@Test
	public void testPostTradeError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		tradeController.validate(trade, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("trade"));
		assertEquals("trade/add", tradeController.validate(trade, bindingResult, model));
	}

	@Test
	public void testUpdateCurve() throws Exception {
		trade.setAccount("test");
		trade.setType("type test");
		trade.setBuyQuantity(20);
		when(tradeService.findTradeById(any())).thenReturn(trade);
		final Model model = new ExtendedModelMap();
		tradeController.showUpdateForm(1, model);
		assertNotNull(model.asMap().get("trade"));
		assertEquals("trade/update", tradeController.showUpdateForm(1, model));
	}

	@Test
	public void testPutTrade() throws Exception {
		trade.setAccount("test");
		trade.setType("type test");
		trade.setBuyQuantity(20);
		when(bindingResult.hasErrors()).thenReturn(false);
		final Model model = new ExtendedModelMap();
		tradeController.updateTrade(1, trade, bindingResult, model);
		assertNotNull(model.asMap().get("trades"));
		assertEquals("redirect:/trade/list", tradeController.updateTrade(1, trade, bindingResult, model));
	}

	@Test
	public void testPUtTradeError() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		final Model model = new ExtendedModelMap();
		tradeController.updateTrade(1, trade, bindingResult, model);
		assertNotNull(model.asMap().get("errors"));
		assertNull(model.asMap().get("trades"));
		assertEquals("trade/update", tradeController.updateTrade(1, trade, bindingResult, model));
	}

	@Test
	public void testDeleteTrade() throws Exception {
		trade.setAccount("test");
		trade.setType("type test");
		trade.setBuyQuantity(20);
		final Model model = new ExtendedModelMap();
		tradeController.deleteTrade(1, model);
		assertNotNull(model.asMap().get("trades"));
		assertEquals("redirect:/trade/list", tradeController.deleteTrade(1, model));
	}
}
