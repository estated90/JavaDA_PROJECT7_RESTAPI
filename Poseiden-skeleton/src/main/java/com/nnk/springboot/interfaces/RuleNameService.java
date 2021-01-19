package com.nnk.springboot.interfaces;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

/**
 * @author nicolas
 *
 */
public interface RuleNameService {

    /**
     * @return List of all rules
     */
	List<RuleName> getAllRuleName();

	/**
	 * @param ruleName The object to update from
	 * @return the object updated
	 */
	RuleName saveRuleNameDb(RuleName ruleName);

	/**
	 * @param id the object id to find
	 * @return Object with the Id provided
	 */
	RuleName findRuleNameById(Integer id);

	/**
	 * @param id the object id to find
	 * @param ruleName The object to update from
	 * @return the object updated
	 */
	RuleName updateRuleName(Integer id, RuleName ruleName);

	/**
	 * @param id the object id to find
	 */
	void deletRuleName(Integer id);

}