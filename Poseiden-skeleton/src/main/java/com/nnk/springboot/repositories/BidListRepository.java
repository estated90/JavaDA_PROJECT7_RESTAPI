package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nicolas
 *
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
