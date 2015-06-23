package com.nana.serviceengine.bean;

import java.util.List;
/**
 * 电影搜索参数
 * @author wds
 *
 */
public class VideoParam {
	
	private String keyWord;
	private String index;
	private List<String> resJson;
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public List<String> getResJson() {
		return resJson;
	}
	public void setResJson(List<String> resJson) {
		this.resJson = resJson;
	}
	
	
}
