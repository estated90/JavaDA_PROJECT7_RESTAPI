package com.nnk.springboot.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
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
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.interfaces.TradeService;
import com.nnk.springboot.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class TradeImplTest {

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private TradeService tradeService;

    @Test
    @Order(1)
    @DisplayName("Saving and deleting a user")
    void testSavingUserDb() {
	LocalDateTime now = LocalDateTime.now();
	Trade trade = new Trade();
	trade.setAccount("test1");
	trade.setType("Purchase");
	trade.setBuyQuantity(20.0);
	trade.setSellQuantity(2.0);
	trade.setBuyPrice(19.0);
	trade.setSellPrice(25);
	trade.setTradeDate(now);
	trade.setSecurity("Security");
	trade.setBook("test1");
	trade.setStatus("ACTIVE");
	trade.setTrader("Just me");
	trade.setBenchmark("test2");
	trade.setBook("book test");
	trade.setCreationName("first test");
	trade.setCreationDate(now);
	trade.setRevisionName("retest");
	trade.setRevisionDate(now.plusDays(1));
	trade.setDealName("Deal name test");
	trade.setDealType("type");
	trade.setSourceListId("coming from");
	trade.setSide("side, whatever it means");
	tradeService.saveTradeDb(trade);
	List<Trade> tradeAll = tradeService.getAllTrade();
	assertEquals(1, tradeAll.size());
	assertNotNull(tradeAll.get(0).getTradeId());
	assertEquals(trade.getAccount(), tradeAll.get(0).getAccount());
	assertEquals(trade.getType(), tradeAll.get(0).getType());
	assertEquals(trade.getBuyQuantity(), tradeAll.get(0).getBuyQuantity());
	assertEquals(trade.getSellQuantity(), tradeAll.get(0).getSellQuantity());
	assertEquals(trade.getBuyPrice(), tradeAll.get(0).getBuyPrice());
	assertEquals(trade.getSellPrice(), tradeAll.get(0).getSellPrice());
	assertNotNull(tradeAll.get(0).getTradeDate());
	assertEquals(trade.getSecurity(), tradeAll.get(0).getSecurity());
	assertEquals(trade.getBook(), tradeAll.get(0).getBook());
	assertEquals(trade.getStatus(), tradeAll.get(0).getStatus());
	assertEquals(trade.getTrader(), tradeAll.get(0).getTrader());
	assertEquals(trade.getBenchmark(), tradeAll.get(0).getBenchmark());
	assertEquals(trade.getBook(), tradeAll.get(0).getBook());
	assertEquals(trade.getCreationName(), tradeAll.get(0).getCreationName());
	assertNotNull(tradeAll.get(0).getCreationDate());
	assertEquals(trade.getRevisionName(), tradeAll.get(0).getRevisionName());
	assertNotNull(tradeAll.get(0).getRevisionDate());
	assertEquals(trade.getDealName(), tradeAll.get(0).getDealName());
	assertEquals(trade.getDealType(), tradeAll.get(0).getDealType());
	assertEquals(trade.getSourceListId(), tradeAll.get(0).getSourceListId());
	assertEquals(trade.getSide(), tradeAll.get(0).getSide());
	int tradeId = tradeAll.get(0).getTradeId();
	tradeService.deleteTrade(tradeId);
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    tradeService.findTradeById(tradeId);
	    ;
	});
	String expectedMessage = "Invalid trade Id:" + tradeId;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(2)
    @DisplayName("Saving and updating a user")
    void testUpdatingUserDb() {
	Trade trade = new Trade();
	trade.setAccount("test1");
	trade.setType("Purchase");
	trade.setBuyQuantity(20.0);
	tradeService.saveTradeDb(trade);
	trade.setAccount("test2");
	trade.setType("test2");
	trade.setBuyQuantity(18);
	int tradeId = tradeRepository.findAll().get(0).getTradeId();
	tradeService.updateTrade(tradeId, trade);
	Trade tradeUpdated = tradeService.findTradeById(tradeId);
	assertEquals(trade.getAccount(), tradeUpdated.getAccount());
	assertEquals(trade.getType(), tradeUpdated.getType());
	assertEquals(trade.getBuyQuantity(), tradeUpdated.getBuyQuantity());
	tradeService.deleteTrade(tradeId);
    }

    @Test
    @Order(3)
    @DisplayName("Deleting but failed")
    void testDeleteFail() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    tradeService.deleteTrade(10);;
	});
	String expectedMessage = "Invalid trade Id:" + 10;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

}
