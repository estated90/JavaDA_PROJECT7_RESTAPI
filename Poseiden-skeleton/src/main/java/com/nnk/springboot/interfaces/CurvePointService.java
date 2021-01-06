package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

/**
 * @author nicolas
 *
 */
public interface CurvePointService {

    /**
     * @return List of all curves
     */
	List<CurvePoint> getAllCurvePoint();

	/**
	 * @param curvePoint The object to update from
	 * @return the object updated
	 */
	CurvePoint saveCurvePointDb(CurvePoint curvePoint);

	/**
	 * @param id the object id to find
	 * @return Object with the Id provided
	 */
	CurvePoint findCurvePointById(Integer id);

	/**
	 * @param id the object id to find
	 * @param curvePoint The object to update from
	 * @return the object updated
	 */
	CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint);

	/**
	 * @param id the object id to find
	 */
	void deletCurvePoint(Integer id);

}