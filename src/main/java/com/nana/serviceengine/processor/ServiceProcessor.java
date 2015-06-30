package com.nana.serviceengine.processor;


import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.bean.UserMessage;

public abstract class ServiceProcessor implements Runnable{
	
	protected UserMessage mes;
	protected Object domainObject;
	
    public ServiceProcessor() {
	
	}
    
    public ServiceProcessor(UserMessage mes) {
    	this.mes = mes;
	}


	protected void sendMsg(String output){
		//RobotConsumerListener.getInstance().sendMsg(output, mes.getReqMessage());
		System.out.println(output);
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
