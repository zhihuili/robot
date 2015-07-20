package com.nana.serviceengine.searcher.bean;

import java.util.List;

public class MethodItem {
	
	private String index;
	private String methodName;
	private List<Param> params;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public List<Param> getParams() {
		return params;
	}
	public void setParams(List<Param> params) {
		this.params = params;
	}
	
	
}
