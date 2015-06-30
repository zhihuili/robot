package com.nana.serviceengine.ruleengine.bean;
/**
 * 规则实体
 * @author wds
 *
 */
public class Rule {
	
	private String id;
	private String data;
	private String domainClass;
	private int authorValue;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDomainClass() {
		return domainClass;
	}
	public void setDomainClass(String domainClass) {
		this.domainClass = domainClass;
	}
	public int getAuthorValue() {
		return authorValue;
	}
	public void setAuthorValue(int authorValue) {
		this.authorValue = authorValue;
	}
	
	
	
}
