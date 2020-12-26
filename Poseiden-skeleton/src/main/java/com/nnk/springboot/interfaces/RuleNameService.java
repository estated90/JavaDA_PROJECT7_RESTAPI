package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {

	List<RuleName> getAllRuleName();

	RuleName saveRuleNameDb(RuleName ruleName);

	RuleName findRuleNameById(Integer id);

	RuleName updateRuleName(Integer id, RuleName ruleName);

	void deletRuleName(Integer id);

}