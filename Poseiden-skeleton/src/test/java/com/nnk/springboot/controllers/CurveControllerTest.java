package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.interfaces.CurvePointService;

@ExtendWith(MockitoExtension.class)
class CurveControllerTest {

    @Mock
    private static CurvePointService curveService;
    @Mock
    private BindingResult bindingResult;
    @Autowired
    private CurvePoint curvePoint;
    @InjectMocks
    private CurveController curveController = new CurveController();

    @BeforeAll
    private static void init() {
    }

    @BeforeEach
    private void setUpPerTest() {
	curvePoint = new CurvePoint();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testAddCurve() throws Exception {
	curvePoint.setCurveId(1);
	curvePoint.setCreationDate(LocalDateTime.now());
	curvePoint.setTerm(20);
	assertEquals("curvePoint/add", curveController.addCurveForm(curvePoint));
    }

    @Test
    void testCurve() throws Exception {
	List<CurvePoint> CurveReturned = new ArrayList<>();
	when(curveService.getAllCurvePoint()).thenReturn(CurveReturned);
	final Model model = new ExtendedModelMap();
	assertEquals("curvePoint/list", curveController.home(model));
	assertNotNull(model.asMap().get("curves"));
    }

    @Test
    void testPostCurve() throws Exception {
	curvePoint.setCurveId(1);
	curvePoint.setCreationDate(LocalDateTime.now());
	curvePoint.setTerm(20);
	List<CurvePoint> CurveReturned = new ArrayList<>();
	CurveReturned.add(curvePoint);
	when(curveService.saveCurvePointDb(any(CurvePoint.class))).thenReturn(curvePoint);
	when(curveService.getAllCurvePoint()).thenReturn(CurveReturned);
	when(bindingResult.hasErrors()).thenReturn(false);
	final Model model = new ExtendedModelMap();
	curveController.validate(curvePoint, bindingResult, model);
	assertNotNull(model.asMap().get("curves"));
	assertEquals("redirect:/curvePoint/list", curveController.validate(curvePoint, bindingResult, model));
    }

    @Test
    void testPostCurveError() throws Exception {
	when(bindingResult.hasErrors()).thenReturn(true);
	final Model model = new ExtendedModelMap();
	curveController.validate(curvePoint, bindingResult, model);
	assertNotNull(model.asMap().get("errors"));
	assertNull(model.asMap().get("curve"));
	assertEquals("curvePoint/add", curveController.validate(curvePoint, bindingResult, model));
    }

    @Test
    void testUpdateCurve() throws Exception {
	curvePoint.setCurveId(1);
	curvePoint.setCreationDate(LocalDateTime.now());
	curvePoint.setTerm(20);
	when(curveService.findCurvePointById(any())).thenReturn(curvePoint);
	final Model model = new ExtendedModelMap();
	curveController.showUpdateForm(1, model);
	assertNotNull(model.asMap().get("curve"));
	assertEquals("curvePoint/update", curveController.showUpdateForm(1, model));
    }

    @Test
    void testPutCurve() throws Exception {
	curvePoint.setCurveId(1);
	curvePoint.setCreationDate(LocalDateTime.now());
	curvePoint.setTerm(20);
	when(bindingResult.hasErrors()).thenReturn(false);
	final Model model = new ExtendedModelMap();
	curveController.updateCurve(1, curvePoint, bindingResult, model);
	assertNotNull(model.asMap().get("curve"));
	assertEquals("redirect:/curvePoint/list", curveController.updateCurve(1, curvePoint, bindingResult, model));
    }

    @Test
    void testPUtCurveError() throws Exception {
	when(bindingResult.hasErrors()).thenReturn(true);
	final Model model = new ExtendedModelMap();
	curveController.updateCurve(1, curvePoint, bindingResult, model);
	assertNotNull(model.asMap().get("errors"));
	assertNull(model.asMap().get("curves"));
	assertEquals("curvePoint/update", curveController.updateCurve(1, curvePoint, bindingResult, model));
    }

    @Test
    void testDeleteCurve() throws Exception {
	curvePoint.setCurveId(1);
	curvePoint.setCreationDate(LocalDateTime.now());
	curvePoint.setTerm(20);
	final Model model = new ExtendedModelMap();
	curveController.deleteCurve(1, model);
	assertNotNull(model.asMap().get("curve"));
	assertEquals("redirect:/curvePoint/list", curveController.deleteCurve(1, model));
    }
}
