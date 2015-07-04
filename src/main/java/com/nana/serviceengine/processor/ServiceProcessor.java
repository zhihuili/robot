package com.nana.serviceengine.processor;


import com.nana.common.message.ResponseMessage;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;

public abstract class ServiceProcessor implements Runnable{
	
	protected UserMessage mes;
	protected Object domainObject;
	
    public ServiceProcessor() {
	
	}
    
    public ServiceProcessor(UserMessage mes) {
    	this.mes = mes;
	}
	
	public UserMessage getMes() {
		return mes;
	}

	public void setMes(UserMessage mes) {
		this.mes = mes;
	}
	
	public Object getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(Object domainObject) {
		this.domainObject = domainObject;
	}
}
