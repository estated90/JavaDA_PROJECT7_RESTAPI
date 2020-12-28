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
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;
import com.nnk.springboot.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class BidListServiceImplTest {

    @Autowired
    private BidListRepository bidListRepository;
    @Autowired
    private BidListService bidListService;

    @Test
    @Order(1)
    @DisplayName("Saving and deleting a bid")
    void testSavingUserDb() {
	LocalDateTime now = LocalDateTime.now();
	BidList bidList = new BidList();
	bidList.setAccount("test1");
	bidList.setAsk(20.0);
	bidList.setAskQuantity(2.0);
	bidList.setBenchmark("test2");
	bidList.setBid(19.0);
	bidList.setBidListDate(now.plusHours(1));
	bidList.setBidQuantity(2.0);
	bidList.setBook("test1");
	bidList.setCommentary("test comment");
	bidList.setCreationDate(now);
	bidList.setCreationName("first test");
	bidList.setDealName("Deal name test");
	bidList.setDealType("type");
	bidList.setRevisionDate(now.plusDays(1));
	bidList.setRevisionName("retest");
	bidList.setSecurity("Security");
	bidList.setSide("side, whatever it means");
	bidList.setSourceListId("coming from");
	bidList.setStatus("ACTIVE");
	bidList.setTrader("Just me");
	bidList.setType("Purchase");
	bidListService.saveBidListDb(bidList);
	List<BidList> bidListAll = bidListService.getAllBidList();
	assertEquals(1, bidListAll.size());
	assertEquals(bidList.getAccount(), bidListAll.get(0).getAccount());
	assertNotNull(bidListAll.get(0).getCreationDate());
	assertEquals(bidList.getBook(), bidListAll.get(0).getBook());
	assertEquals(bidList.getBid(), bidListAll.get(0).getBid());
	int bidListId = bidListAll.get(0).getBidListId();
	bidListService.deletBidList(bidListId);
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    bidListService.findById(bidListId);
	    ;
	});
	String expectedMessage = "Invalid bidlist Id:" + bidListId;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(2)
    @DisplayName("Saving and updating a bid")
    void testUpdatingUserDb() {
	BidList bidList = new BidList();
	bidList.setAccount("test1");
	bidList.setType("Sells");
	bidListService.saveBidListDb(bidList);
	bidList.setAccount("test2");
	bidList.setType("Purchase");
	int bidListId = bidListRepository.findAll().get(0).getBidListId();
	bidListService.updateBidList(bidListId, bidList);
	BidList bidListUpdated = bidListService.findById(bidListId);
	assertEquals(bidList.getAccount(), bidListUpdated.getAccount());
	assertEquals(bidList.getType(), bidListUpdated.getType());
	bidListService.deletBidList(bidListId);
    }

    @Test
    @Order(3)
    @DisplayName("Deleting but failed")
    void testDeleteFail() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    bidListService.deletBidList(10);
	});
	String expectedMessage = "Invalid bid List Id:" + 10;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }
}
