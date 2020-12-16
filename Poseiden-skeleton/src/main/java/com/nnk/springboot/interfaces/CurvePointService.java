package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

	List<CurvePoint> getAllCurvePoint();

	CurvePoint saveCurvePointDb(CurvePoint curvePoint);

	CurvePoint findCurvePointById(Integer id);

	CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint);

	void deletCurvePoint(Integer id);

}