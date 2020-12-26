package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "RuleName")
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", columnDefinition = "TINYINT", nullable = false)
	private Integer id;
	@Column(name = "name", length = 125)
	@NotBlank(message = "name is mandatory")
	private String name;
	@Column(name = "description", length = 125)
	@NotBlank(message = "description is mandatory")
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
	 * @return the id
	 */
	public Integer getId() {
	    return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
