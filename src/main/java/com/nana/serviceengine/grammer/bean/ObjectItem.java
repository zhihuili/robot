package com.nana.serviceengine.grammer.bean;

import java.util.ArrayList;
import java.util.List;

public class ObjectItem {
	//连词 如果没连词则放谓语
	private String connectWord;
	//连词坐标 
	private int connectIndex;
	private int index;
	private List<String> attributive = new ArrayList<String>();
	private String value;
	
	public int getConnectIndex() {
		return connectIndex;
	}
	public void setConnectIndex(int connectIndex) {
		this.connectIndex = connectIndex;
	}
	public String getConnectWord() {
		return connectWord;
	}
	public void setConnectWord(String connectWord) {
		this.connectWord = connectWord;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public List<String> getAttributive() {
		return attributive;
	}
	public void setAttributive(List<String> attributive) {
		this.attributive = attributive;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
