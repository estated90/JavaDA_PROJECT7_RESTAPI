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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;

@ExtendWith(MockitoExtension.class)
class BidListControllerTest {

    @Mock
    private static BidListService bidListService;
    @Mock
    private BindingResult bindingResult;
    @Autowired
    private BidList bidList;
    @InjectMocks
    private BidListController bidListController = new BidListController();

    @BeforeAll
    private static void init() {
    }

    @BeforeEach
    private void setUpPerTest() {
	bidList = new BidList();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testAddListBidList() throws Exception {
	bidList.setAccount("test");
	bidList.setType("type test");
	bidList.setBidQuantity(20);
	assertEquals("bidList/add", bidListController.addBidForm(bidList));
    }

    @Test
    void testListBidList() throws Exception {
	List<BidList> bidListReturned = new ArrayList<>();
	when(bidListService.getAllBidList()).thenReturn(bidListReturned);
	final Model model = new ExtendedModelMap();
	assertEquals("bidList/list", bidListController.home(model));
	assertNotNull(model.asMap().get("bidlists"));
    }

    @Test
    void testPostBidList() throws Exception {
	bidList.setAccount("test");
	bidList.setType("type test");
	bidList.setBidQuantity(20);
	List<BidList> bidListReturned = new ArrayList<>();
	bidListReturned.add(bidList);
	when(bidListService.saveBidListDb(any(BidList.class))).thenReturn(bidList);
	when(bidListService.getAllBidList()).thenReturn(bidListReturned);
	when(bindingResult.hasErrors()).thenReturn(false);
	final Model model = new ExtendedModelMap();
	bidListController.validate(bidList, bindingResult, model);
	assertNotNull(model.asMap().get("bidlist"));
	assertEquals("redirect:/bidList/list", bidListController.validate(bidList, bindingResult, model));
    }

    @Test
    void testPostBidListError() throws Exception {
	when(bindingResult.hasErrors()).thenReturn(true);
	final Model model = new ExtendedModelMap();
	bidListController.validate(bidList, bindingResult, model);
	assertNotNull(model.asMap().get("errors"));
	assertNull(model.asMap().get("bidList"));
	assertEquals("bidList/add", bidListController.validate(bidList, bindingResult, model));
    }

    @Test
    void testUpdateBidList() throws Exception {
	bidList.setBidListId(1);
	bidList.setAccount("test");
	bidList.setType("type test");
	bidList.setBidQuantity(20);
	when(bidListService.findById(any())).thenReturn(bidList);
	final Model model = new ExtendedModelMap();
	bidListController.showUpdateForm(1, model);
	assertNotNull(model.asMap().get("bidList"));
	assertEquals("bidList/update", bidListController.showUpdateForm(1, model));
    }

    @Test
    void testPutBidList() throws Exception {
	bidList.setBidListId(1);
	bidList.setAccount("test");
	bidList.setType("type test");
	bidList.setBidQuantity(20);
	when(bidListService.findById(any())).thenReturn(bidList);
	when(bindingResult.hasErrors()).thenReturn(false);
	final Model model = new ExtendedModelMap();
	bidListController.updateBid(1, bidList, bindingResult, model);
	assertNotNull(model.asMap().get("bidlists"));
	assertEquals("redirect:/bidList/list", bidListController.updateBid(1, bidList, bindingResult, model));
    }

    @Test
    void testPUtBidListError() throws Exception {
	when(bindingResult.hasErrors()).thenReturn(true);
	final Model model = new ExtendedModelMap();
	bidListController.updateBid(1, bidList, bindingResult, model);
	assertNotNull(model.asMap().get("errors"));
	assertNull(model.asMap().get("bidLists"));
	assertEquals("bidList/update", bidListController.updateBid(1, bidList, bindingResult, model));
    }

    @Test
    void testDeleteBidList() throws Exception {
	bidList.setBidListId(1);
	bidList.setAccount("test");
	bidList.setType("type test");
	bidList.setBidQuantity(20);
	final Model model = new ExtendedModelMap();
	bidListController.deleteBid(1, model);
	assertNotNull(model.asMap().get("bidlists"));
	assertEquals("redirect:/bidList/list", bidListController.deleteBid(1, model));
    }
}
