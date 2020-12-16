package com.nnk.springboot.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.interfaces.CurvePointService;
import com.nnk.springboot.repositories.CurvePointRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class CurvePointImplTest {

    @Autowired
    private CurvePointRepository curvePointRepository;
    @Autowired
    private CurvePointService curvePointService;

    @Test
    @Order(1)
    @DisplayName("Saving and deleting a user")
    void testSavingUserDb() {
	LocalDateTime now = LocalDateTime.now();
	CurvePoint curvePoint = new CurvePoint();
	curvePoint.setCurveId(1);
	curvePoint.setAsOfDate(now);
	curvePoint.setTerm(300);
	curvePoint.setValue(100);
	curvePoint.setCreationDate(now.minusDays(6));
	curvePointService.saveCurvePointDb(curvePoint);
	List<CurvePoint> curvePointAll = curvePointService.getAllCurvePoint();
	assertEquals(1, curvePointAll.size());
	assertEquals(curvePoint.getCurveId(), curvePointAll.get(0).getCurveId());
	assertNotNull(curvePointAll.get(0).getAsOfDate());
	assertEquals(curvePoint.getTerm(), curvePointAll.get(0).getTerm());
	assertEquals(curvePoint.getValue(), curvePointAll.get(0).getValue());
	assertNotNull(curvePointAll.get(0).getCreationDate());
	int curvePointId = curvePointAll.get(0).getId();
	curvePointService.deletCurvePoint(curvePointId);
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    curvePointService.findCurvePointById(curvePointId);
	    ;
	});
	String expectedMessage = "Invalid curve point Id:" + curvePointId;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(2)
    @DisplayName("Saving and updating a user")
    void testUpdatingUserDb() {
	LocalDateTime now = LocalDateTime.now();
	CurvePoint curvePoint = new CurvePoint();
	curvePoint.setCurveId(1);
	curvePoint.setAsOfDate(now);
	curvePoint.setTerm(300);
	curvePoint.setValue(100);
	curvePoint.setCreationDate(now.minusDays(6));
	curvePointService.saveCurvePointDb(curvePoint);
	curvePoint.setTerm(400);
	curvePoint.setValue(200);
	int curvePointId = curvePointRepository.findAll().get(0).getId();
	curvePointService.updateCurvePoint(curvePointId, curvePoint);
	CurvePoint curvePointUpdated = curvePointService.findCurvePointById(curvePointId);
	assertEquals(curvePoint.getTerm(), curvePointUpdated.getTerm());
	assertEquals(curvePoint.getValue(), curvePointUpdated.getValue());
	curvePointService.deletCurvePoint(curvePointId);
    }

    @Test
    @Order(3)
    @DisplayName("Deleting but failed")
    void testDeleteFail() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    curvePointService.deletCurvePoint(10);
	});
	String expectedMessage = "Invalid curve point Id:" + 10;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

}
