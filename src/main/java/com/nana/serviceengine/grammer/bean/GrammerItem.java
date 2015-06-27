package com.nana.serviceengine.grammer.bean;

import java.util.ArrayList;
import java.util.List;

public class GrammerItem {
	//主语
	private String subItem;
	//否定词
	private String notItem;
	//谓词
	private String predicItem; 
	//宾语，其中带连词和定语
	private List<ObjectItem> objects = new ArrayList<ObjectItem>();
	
	public String getSubItem() {
		return subItem;
	}
	public void setSubItem(String subItem) {
		this.subItem = subItem;
	}
	public String getNotItem() {
		return notItem;
	}
	public void setNotItem(String notItem) {
		this.notItem = notItem;
	}
	public String getPredicItem() {
		return predicItem;
	}
	public void setPredicItem(String predicItem) {
		this.predicItem = predicItem;
	}
	public List<ObjectItem> getObjects() {
		return objects;
	}
	public void setObjects(List<ObjectItem> objects) {
		this.objects = objects;
	}
	
	
	
	
}
