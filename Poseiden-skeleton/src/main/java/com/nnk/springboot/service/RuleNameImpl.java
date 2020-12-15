package com.nnk.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.interfaces.RuleNameService;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameImpl implements RuleNameService {

	private static final Logger logger = LogManager.getLogger("RuleNameImpl");
	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Override
	public List<RuleName> getAllRuleName() {
		logger.info("Getting all the rule name from db");
		List<RuleName> ruleName = ruleNameRepository.findAll();
		logger.info("rule name returned :{}", ruleName);
		return ruleName;
	}

	@Override
	public RuleName saveRuleNameDb(RuleName ruleName) {
		logger.info("Saving new rule name:{}",ruleName);
		ruleNameRepository.save(ruleName);
		logger.info("rule name created:{}",ruleName);
		return ruleName;
	}

	@Override
	public RuleName findRuleNameById(Integer id) {
		logger.info("Finding the rule name with id :{}", id);
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
		logger.info("Returning the rule name with id :{} : {}", id, ruleName);
		return ruleName;
	}

	@Override
	public RuleName updateRuleName(Integer id, RuleName ruleName) {
		logger.info("Updating the rule name with id :{}", id);
		ruleName.setId(id);
		ruleNameRepository.save(ruleName);
		logger.info("The rule name with id :{} was updated : {}", id, ruleName);
		return ruleName;
	}

	@Override
	public void deletRuleName(Integer id) {
		logger.info("Deleting the rule name with id :{}", id);
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rule name Id:" + id));
		ruleNameRepository.delete(ruleName);
		logger.info("rule name with id :{} was deleted", id);
	}
}
