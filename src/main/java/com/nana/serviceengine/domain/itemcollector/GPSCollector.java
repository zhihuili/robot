package com.nana.serviceengine.domain.itemcollector;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class GPSCollector extends Collector<GPS>{
	private static GPSCollector gpsCollector = new GPSCollector();

	private GPSCollector() {
	}

	public static GPSCollector getInstance() {
		return gpsCollector;
	}

	public GPS collectParam(ParamItem paramItem,UserMessage message,ServiceProcessor processor) {		
		return message.getGps();
	}

	@Override
	public GPS initCollectParam(UserMessage message, ServiceProcessor processor) {
		return collectParam(null,message,processor);
	}

	@Override
	public GPS lackCollectParam(UserMessage message, ServiceProcessor processor) {
		return collectParam(null,message,processor);
	}

	@Override
	public GPS finishCollectParam(ParamItem paramItem, UserMessage message,
			ServiceProcessor processor) {
		return collectParam(paramItem,message,processor);
	}
}
