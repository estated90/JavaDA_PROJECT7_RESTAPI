package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.interfaces.CurvePointService;

/**
 * @author nicolas
 * <p>Controller CRUD to access the curve data</p>
 *
 */
@Controller
public class CurveController {

	private static final Logger logger = LogManager.getLogger("CurveController");
	@Autowired
	private CurvePointService curveService;
	private static final String CURVE = "curve";
	private static final String REDIRECT = "redirect:/curvePoint/list";

	/**
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/curvePoint/list")
	public String home(Model model) {
		logger.info("Getting all curve point of DB");
		model.addAttribute("curves", curveService.getAllCurvePoint());
		return "curvePoint/list";
	}

	/**
	 * @param bid as an object
	 * @return the link of page
	 */
	@GetMapping("/curvePoint/add")
	public String addCurveForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	/**
	 * @param bid as an object
	 * @param result control validity object
	 * @param model to add from
	 * @return the link of page
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.info("Creation of the curve point : {}", curvePoint);
		if (result.hasErrors()) {
			logger.error("curve point data was not valid : {}", curvePoint);
			model.addAttribute("errors",result.getAllErrors());
			return "curvePoint/add";
		}
		curveService.saveCurvePointDb(curvePoint);
		model.addAttribute("curves", curveService.getAllCurvePoint());
		logger.info("{} has been created in the db", curvePoint);
		return REDIRECT;
	}

	/**
	 * @param id of the bid as int
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting curve point with id : {}", id);
		CurvePoint curvePoint = curveService.findCurvePointById(id);
		model.addAttribute(CURVE, curvePoint);
		logger.info("Returning curve point : {}", curvePoint);
		return "curvePoint/update";
	}

	/**
	 * @param id of the bid as int
	 * @param bidList as an object
	 * @param result control validity object
	 * @param model to add from
	 * @return the link of page
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		logger.info("Updating curve point : {} with id : {}", curvePoint, id);
		if (result.hasErrors()) {
			logger.info("curve point was not valid : {} with id : {}", curvePoint, id);
			model.addAttribute("errors",result.getAllErrors());
			return "curvePoint/update";
		}
		curveService.updateCurvePoint(id, curvePoint);
		model.addAttribute(CURVE, curveService.getAllCurvePoint());
		logger.info("curve point was udpated");
		return REDIRECT;
	}

	/**
	 * @param id of the bid as int
	 * @param model to add from
	 * @return the link of page
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurve(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting curve point with id : {}", id);
		curveService.deletCurvePoint(id);
		model.addAttribute(CURVE, curveService.getAllCurvePoint());
		logger.info("curve point was deleted");
		return REDIRECT;
	}
}
