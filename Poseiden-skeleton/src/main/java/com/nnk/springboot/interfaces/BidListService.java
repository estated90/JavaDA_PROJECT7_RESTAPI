package com.nnk.springboot.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;

/**
 * @author nicolas
 *
 */
@Service
public interface BidListService {

    /**
     * @return List of all bids
     */
    List<BidList> getAllBidList();

	/**
	 * @param id the object id to find
	 * @return Object with the Id provided
	 */
	BidList findById(Integer id);

	/**
	 * @param id the object id to find
	 * @param bidList The object to update from
	 * @return the object updated
	 */
	BidList updateBidList(Integer id, BidList bidList);

	/**
	 * @param id the object id to find
	 */
	void deletBidList(Integer id);

	/**
	 * @param bidList The object to update from
	 * @return the object updated
	 */
	BidList saveBidListDb(BidList bidList);

}