package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

	List<CurvePoint> getAllBidList();

	CurvePoint saveUserDb(CurvePoint curvePoint);

	CurvePoint findById(Integer id);

	CurvePoint updateBidList(Integer id, CurvePoint curvePoint);

	void deletBidList(Integer id);

}