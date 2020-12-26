package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Application;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class BidListControllerTest {

	@Mock
	private static BidListService bidListService;
	@Autowired
	private MockMvc mockMvc;
	private BidList bidList;

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
	public void testListBidList() throws Exception {
		this.mockMvc.perform(get("/bidList/list")).andExpect(status().isOk()).andExpect(view().name("bidList/list"));
	}

	@Test
	public void testAddListBidList() throws Exception {
		this.mockMvc.perform(get("/bidList/add")).andExpect(status().isOk()).andExpect(view().name("bidList/add"));
	}

	@Test
	public void testPostListBidList() throws Exception {
		bidList.setAccount("test");
		bidList.setType("type test");
		bidList.setBidQuantity(20);
		List<BidList> bidListReturned = new ArrayList<>();
		bidListReturned.add(bidList);
		when(bidListService.saveBidListDb(any(BidList.class))).thenReturn(bidList);
		when(bidListService.getAllBidList()).thenReturn(bidListReturned);
		String bidListAsString = asJsonString(bidList);
		this.mockMvc.perform(post("/bidList/validate").contentType(MediaType.APPLICATION_JSON).content(bidListAsString))
				.andExpect(status().isOk()).andExpect(view().name("bidList/list"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}