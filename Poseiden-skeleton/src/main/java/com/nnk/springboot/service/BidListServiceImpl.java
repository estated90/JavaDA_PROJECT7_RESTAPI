package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.interfaces.BidListService;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class BidListServiceImpl implements BidListService {

	private static final Logger logger = LogManager.getLogger("BidListServiceImpl");
	@Autowired
	private BidListRepository bidListRepository;
	@Autowired
	private UserRepository userservice;

	
	@Override
	@Transactional(readOnly=true)
	public List<BidList> getAllBidList() {
		logger.info("Getting all the bid list from db");
		List<BidList> bidList = bidListRepository.findAll();
		logger.info("BidList returned :{}", bidList);
		return bidList;
	}

	@Override
	@Transactional
	public BidList saveBidListDb(BidList bidList) {
		logger.info("Saving new bid list:{}", bidList);
		String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
		bidList.setTrader(userservice.findByUsername(currentPrincipalName).getFullname());
		bidListRepository.save(bidList);
		logger.info("Bid list created:{}", bidList);
		return bidList;
	}

	@Override
	@Transactional(readOnly=true)
	public BidList findById(Integer id) {
		logger.info("Finding the bid list with id :{}", id);
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
		logger.info("Returning the bid list with id :{} : {}", id, bidList);
		return bidList;
	}

	@Override
	@Transactional
	public BidList updateBidList(Integer id, BidList bidList) {
		logger.info("Updating the bid list with id :{}", id);
		bidList.setBidListId(id);
		bidListRepository.save(bidList);
		logger.info("The bid list with id :{} was updated : {}", id, bidList);
		return bidList;
	}

	@Override
	@Transactional
	public void deletBidList(Integer id) {
		logger.info("Deleting the bid list with id :{}", id);
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid List Id:" + id));
		bidListRepository.delete(bidList);
		logger.info("Bid list with id :{} was deleted", id);
	}
}
