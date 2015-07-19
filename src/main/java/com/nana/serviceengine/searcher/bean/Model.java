package com.nana.serviceengine.searcher.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
	
	public Model(){
		methodItems = new HashMap<String,MethodItem>();
		reducer = new ArrayList<String>();
		beanItems = new ArrayList<BeanItem>();
	}
	
	private Map<String,MethodItem> methodItems;
	private List<String> reducer;
	private List<BeanItem> beanItems;
	
	
	public Map<String, MethodItem> getMethodItems() {
		return methodItems;
	}
	public void setMethodItems(Map<String, MethodItem> methodItems) {
		this.methodItems = methodItems;
	}
	public List<String> getReducer() {
		return reducer;
	}
	public void setReducer(List<String> reducer) {
		this.reducer = reducer;
	}
	public List<BeanItem> getBeanItems() {
		return beanItems;
	}
	public void setBeanItems(List<BeanItem> beanItems) {
		this.beanItems = beanItems;
	}
    
	
}
