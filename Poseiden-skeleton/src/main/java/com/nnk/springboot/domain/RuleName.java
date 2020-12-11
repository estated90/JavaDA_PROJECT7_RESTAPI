package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RuleName")
public class RuleName {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id", columnDefinition = "TINYINT", nullable = false)
	private int id;
	@Column(name = "name", length = 125)
	private String name;
	@Column(name = "description", length = 125)
	private String description;
	@Column(name = "json", length = 125)
	private String json;
	@Column(name = "template", length = 125)
	private String template;
	@Column(name = "sqlPart", length = 125)
	private String sqlPart;
	@Column(name = "sqlStr", length = 125)
	private String sqlStr;
	
	
	
	/**
	 * @param name
	 * @param description
	 * @param json
	 * @param template
	 * @param sqlPart
	 * @param sqlStr
	 */
	public RuleName(String name, String description, String json, String template, String sqlPart, String sqlStr) {
	    super();
	    this.name = name;
	    this.description = description;
	    this.json = json;
	    this.template = template;
	    this.sqlPart = sqlPart;
	    this.sqlStr = sqlStr;
	}
	/**
	 * @return the id
	 */
	public int getId() {
	    return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
	    this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
	    return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
	    return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
	    this.description = description;
	}
	/**
	 * @return the json
	 */
	public String getJson() {
	    return json;
	}
	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
	    this.json = json;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
	    return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
	    this.template = template;
	}
	/**
	 * @return the sqlPart
	 */
	public String getSqlPart() {
	    return sqlPart;
	}
	/**
	 * @param sqlPart the sqlPart to set
	 */
	public void setSqlPart(String sqlPart) {
	    this.sqlPart = sqlPart;
	}
	/**
	 * @return the sqlStr
	 */
	public String getSqlStr() {
	    return sqlStr;
	}
	/**
	 * @param sqlStr the sqlStr to set
	 */
	public void setSqlStr(String sqlStr) {
	    this.sqlStr = sqlStr;
	}
}
