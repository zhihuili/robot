package com.nana.serviceengine.neuron.processor.factory;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class ProcessorFactory {
	
	private static ProcessorFactory processFactory = new ProcessorFactory();
	
	private ProcessorFactory(){
		
	}
	
	public static ProcessorFactory getInstance(){
		return processFactory;
	} 
	
	
	public ServiceProcessor createProcessorByClassName(String className,UserMessage mes){
		try {
			Class c = Class.forName(className);
			ServiceProcessor sp = (ServiceProcessor)c.newInstance();
			sp.setMes(mes);
			return sp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
