package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListServiceImpl implements BidListService {

    private static final Logger logger = LogManager.getLogger("BidListServiceImpl");
    @Autowired
    private BidListRepository bidListRepository;
    
    @Override
    public List<BidList> getAllBidList() {
	logger.info("Getting all the bid list from db");
	List<BidList> bidList = bidListRepository.findAll();
	logger.info("BidList returned :{}", bidList);
	return bidList;
    }
    
    
}
