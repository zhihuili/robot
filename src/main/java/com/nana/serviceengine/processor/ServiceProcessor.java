package com.nana.serviceengine.processor;

import com.nana.serviceengine.bean.UserMessage;

public abstract class ServiceProcessor implements Runnable{
	
	protected UserMessage mes;
	
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
	
	
}
