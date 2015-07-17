package com.nana.serviceengine.searcher.bean;

/**
 * 获取Param的正则
 * @author wds
 *
 */
public class ItemRule {
	//前规则
	private String firstPattern;
	//要获取的参数名称
	private String valueName;
	//后规则
	private String endPattern;
	//需要去除的停用词组成的字符串
	private String stopWords;
	
	public String getFirstPattern() {
		return firstPattern;
	}
	public void setFirstPattern(String firstPattern) {
		this.firstPattern = firstPattern;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public String getEndPattern() {
		return endPattern;
	}
	public void setEndPattern(String endPattern) {
		this.endPattern = endPattern;
	}
	public String getStopWords() {
		return stopWords;
	}
	public void setStopWords(String stopWords) {
		this.stopWords = stopWords;
	}
	
	
}
