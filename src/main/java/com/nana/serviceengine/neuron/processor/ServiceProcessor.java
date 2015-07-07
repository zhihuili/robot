package com.nana.serviceengine.neuron.processor;


import com.nana.common.message.ResponseMessage;
import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;

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
