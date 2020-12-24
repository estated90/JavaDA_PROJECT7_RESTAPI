package com.nnk.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.interfaces.CurvePointService;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointImpl implements CurvePointService {

	private static final Logger logger = LogManager.getLogger("CurvePointImpl");
	@Autowired
	private CurvePointRepository curvePointRepository;

	
	@Override
	@Transactional(readOnly=true)
	public List<CurvePoint> getAllCurvePoint() {
		logger.info("Getting all the curve point from db");
		List<CurvePoint>  curvePoint = curvePointRepository.findAll();
		logger.info("curve point returned :{}", curvePoint);
		return curvePoint;
	}

	
	@Override
	@Transactional
	public CurvePoint saveCurvePointDb(CurvePoint curvePoint) {
		logger.info("Saving new curve point:{}",curvePoint);
		curvePoint.setCreationDate(LocalDateTime.now());
		curvePoint.setAsOfDate(LocalDateTime.now());
		curvePointRepository.save(curvePoint);
		logger.info("curve point created:{}",curvePoint);
		return curvePoint;
	}

	@Override
	@Transactional(readOnly=true)
	public CurvePoint findCurvePointById(Integer id) {
		logger.info("Finding the curve point with id :{}", id);
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
		logger.info("Returning the curve point with id :{} : {}", id, curvePoint);
		return curvePoint;
	}

	
	@Override
	@Transactional
	public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) {
		logger.info("Updating the curve point with id :{}", id);
		curvePoint.setId(id);
		curvePointRepository.save(curvePoint);
		logger.info("The curve point with id :{} was updated : {}", id, curvePoint);
		return curvePoint;
	}

	
	@Override
	@Transactional
	public void deletCurvePoint(Integer id) {
		logger.info("Deleting the curve point with id :{}", id);
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
		curvePointRepository.delete(curvePoint);
		logger.info("curve point with id :{} was deleted", id);
	}
}
