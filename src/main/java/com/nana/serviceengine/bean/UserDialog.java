package com.nana.serviceengine.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.DialogState;

public class UserDialog {
	
	private String userid;
	private int count;
	private List<String> dialog=new ArrayList<String>();
	private Map<String,Object> stateInfo = new HashMap<String,Object>();
	private List<ServiceProcessor> processors;
	private Date lastDialog;
	private DialogState state;
	
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
	public List<ServiceProcessor> getProcessors() {
		return processors;
	}
	public void setProcessors(List<ServiceProcessor> processors) {
		this.processors = processors;
	}
	public Date getLastDialog() {
		return lastDialog;
	}
	public void setLastDialog(Date lastDialog) {
		this.lastDialog = lastDialog;
	}
	public DialogState getState() {
		return state;
	}
	public void setState(DialogState state) {
		this.state = state;
	}
	public Map<String, Object> getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(Map<String, Object> stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	
	
}
