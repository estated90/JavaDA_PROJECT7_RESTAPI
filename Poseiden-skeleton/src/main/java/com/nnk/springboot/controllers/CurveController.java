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
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.interfaces.CurvePointService;

@Controller
public class CurveController {

	private static final Logger logger = LogManager.getLogger("CurveController");
	@Autowired
	private CurvePointService curveService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		logger.info("Getting all curve point of DB");
		model.addAttribute("curves", curveService.getAllCurvePoint());
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.info("Creation of the curve point : {}", curvePoint);
		if (result.hasErrors()) {
			logger.error("curve point data was not valid : {}", curvePoint);
			return "curvePoint/add";
		}
		curveService.saveCurvePointDb(curvePoint);
		model.addAttribute("curves", curveService.getAllCurvePoint());
		logger.info("{} has been created in the db", curvePoint);
		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("Getting curve point with id : {}", id);
		CurvePoint curvePoint = curveService.findCurvePointById(id);
		model.addAttribute("curve", curvePoint);
		logger.info("Returning curve point : {}", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		logger.info("Updating curve point : {} with id : {}", curvePoint, id);
		if (result.hasErrors()) {
			logger.info("curve point was not valid : {} with id : {}", curvePoint, id);
			return "curvePoint/update";
		}
		curveService.updateCurvePoint(id, curvePoint);
		model.addAttribute("curve", curveService.getAllCurvePoint());
		logger.info("curve point was udpated");
		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		logger.info("Deleting curve point with id : {}", id);
		curveService.deletCurvePoint(id);
		model.addAttribute("curve", curveService.getAllCurvePoint());
		logger.info("curve point was deleted");
		return "redirect:/curvePoint/list";
	}
}
