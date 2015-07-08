package com.nana.serviceengine.common.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.bean.DialogState;

public class UserDialog {
	
	private String userid;
	//第几次会话
	private int count;
	private List<String> dialog=new ArrayList<String>();
	private List<ResponseMessageAdapter> resMessages = new ArrayList<ResponseMessageAdapter>();
	private Map<String,Object> stateInfo = new HashMap<String,Object>();
	private List<ServiceProcessor> processors;
	//上一次会话状态
	private DialogState preDialogState;
	//当前会话的领域参数
	private Object param;
	//上一次会话的时间
	private Date lastDialog;
	//会话状态
	private DialogState state;
	
	private String currentDomain;
	
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
	public DialogState getPreDialogState() {
		return preDialogState;
	}
	public void setPreDialogState(DialogState preDialogState) {
		this.preDialogState = preDialogState;
	}
	
	public List<ResponseMessageAdapter> getResMessages() {
		return resMessages;
	}
	public void setResMessages(List<ResponseMessageAdapter> resMessages) {
		this.resMessages = resMessages;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public String getCurrentDomain() {
		return currentDomain;
	}
	public void setCurrentDomain(String currentDomain) {
		this.currentDomain = currentDomain;
	}
	
}
