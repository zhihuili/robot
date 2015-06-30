package com.nana.serviceengine.grammer.bean;

public class SentenceRuleItem {
	// 句式正则
	private String mainRule;
	// 前置规则
	private String parRule;
	// 开始偏移
	private Integer startDiff;
	// 结束偏移
	private Integer endDiff;
	// 补充规则
	private String subRule;

	public SentenceRuleItem(String mainRule, String parRule, Integer startDiff,
			Integer endDiff, String subRule) {
		super();
		this.mainRule = mainRule;
		this.parRule = parRule;
		this.startDiff = startDiff;
		this.endDiff = endDiff;
		this.subRule = subRule;
	}

	public String getMainRule() {
		return mainRule;
	}

	public void setMainRule(String mainRule) {
		this.mainRule = mainRule;
	}

	public String getParRule() {
		return parRule;
	}

	public void setParRule(String parRule) {
		this.parRule = parRule;
	}

	public Integer getStartDiff() {
		return startDiff;
	}

	public void setStartDiff(Integer startDiff) {
		this.startDiff = startDiff;
	}

	public Integer getEndDiff() {
		return endDiff;
	}

	public void setEndDiff(Integer endDiff) {
		this.endDiff = endDiff;
	}

	public String getSubRule() {
		return subRule;
	}

	public void setSubRule(String subRule) {
		this.subRule = subRule;
	}

}
