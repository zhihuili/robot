package com.nana.serviceengine.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nana.serviceengine.processor.ServiceProcessor;

public class UserDialog {
	
	private String userid;
	private int count;
	private List<String> dialog=new ArrayList<String>();
	private ServiceProcessor processor;
	private Object domainObject;
	private Date lastDialog;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getDialog() {
		return dialog;
	}
	public void setDialog(List<String> dialog) {
		this.dialog = dialog;
	}
	public Object getDomainObject() {
		return domainObject;
	}
	public void setDomainObject(Object domainObject) {
		this.domainObject = domainObject;
	}
	public ServiceProcessor getProcessor() {
		return processor;
	}
	public void setProcessor(ServiceProcessor processor) {
		this.processor = processor;
	}
	public Date getLastDialog() {
		return lastDialog;
	}
	public void setLastDialog(Date lastDialog) {
		this.lastDialog = lastDialog;
	}
	
	
}
