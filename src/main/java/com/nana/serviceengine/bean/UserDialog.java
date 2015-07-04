package com.nana.serviceengine.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.common.message.ResponseMessage;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.DialogState;

public class UserDialog {
	
	private String userid;
	//第几次会话
	private int count;
	private List<String> dialog=new ArrayList<String>();
	private List<ResponseMessage> resMessages = new ArrayList<ResponseMessage>();
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
	public List<ResponseMessage> getResMessages() {
		return resMessages;
	}
	public void setResMessages(List<ResponseMessage> resMessages) {
		this.resMessages = resMessages;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}

	
	
	
	
}
