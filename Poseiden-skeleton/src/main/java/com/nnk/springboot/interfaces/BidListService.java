package com.nnk.springboot.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;

@Service
public interface BidListService {

    List<BidList> getAllBidList();

}