package com.nnk.springboot.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.interfaces.RuleNameService;
import com.nnk.springboot.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class RuleNameImplTest {

    @Autowired
    private RuleNameRepository ruleNameRepository;
    @Autowired
    private RuleNameService ruleNameService;

    @Test
    @Order(1)
    @DisplayName("Saving and deleting a user")
    void testSavingUserDb() {
	RuleName ruleName = new RuleName();
	ruleName.setName("test1");
	ruleName.setDescription("description test 1");
	ruleName.setJson("test json");
	ruleName.setTemplate("this is a template");
	ruleName.setSqlPart("sqlPart test");
	ruleName.setSqlStr("sqlString test");
	ruleNameService.saveRuleNameDb(ruleName);
	List<RuleName> ruleNameAll = ruleNameService.getAllRuleName();
	assertEquals(1, ruleNameAll.size());
	assertNotNull(ruleNameAll.get(0).getId());
	assertEquals(ruleName.getName(), ruleNameAll.get(0).getName());
	assertEquals(ruleName.getDescription(), ruleNameAll.get(0).getDescription());
	assertEquals(ruleName.getJson(), ruleNameAll.get(0).getJson());
	assertEquals(ruleName.getTemplate(), ruleNameAll.get(0).getTemplate());
	assertEquals(ruleName.getSqlPart(), ruleNameAll.get(0).getSqlPart());
	assertEquals(ruleName.getSqlStr(), ruleNameAll.get(0).getSqlStr());
	int ruleNameId = ruleNameAll.get(0).getId();
	ruleNameService.deletRuleName(ruleNameId);
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    ruleNameService.findRuleNameById(ruleNameId);
	    ;
	});
	String expectedMessage = "Invalid rule name Id:" + ruleNameId;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(2)
    @DisplayName("Saving and updating a user")
    void testUpdatingUserDb() {
	RuleName ruleName = new RuleName();
	ruleName.setName("test1");
	ruleName.setDescription("description test 1");
	ruleName.setJson("test json");
	ruleName.setTemplate("this is a template");
	ruleName.setSqlPart("sqlPart test");
	ruleName.setSqlStr("sqlString test");
	ruleNameService.saveRuleNameDb(ruleName);
	ruleName.setName("test2");
	ruleName.setDescription("test2");
	int ruleNameId = ruleNameRepository.findAll().get(0).getId();
	ruleNameService.updateRuleName(ruleNameId, ruleName);
	RuleName curvePointUpdated = ruleNameService.findRuleNameById(ruleNameId);
	assertEquals(ruleName.getName(), curvePointUpdated.getName());
	assertEquals(ruleName.getDescription(), curvePointUpdated.getDescription());
	ruleNameService.deletRuleName(ruleNameId);
    }

    @Test
    @Order(3)
    @DisplayName("Deleting but failed")
    void testDeleteFail() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    ruleNameService.deletRuleName(10);;
	});
	String expectedMessage = "Invalid rule name Id:" + 10;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

}
