package com.nana.serviceengine.domain.itemcollector;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class GPSCollector implements Collector<GPS>{
	private static GPSCollector gpsCollector = new GPSCollector();

	private GPSCollector() {
	}

	public static GPSCollector getInstance() {
		return gpsCollector;
	}

	@Override
	public GPS getParam(ParamItem paramItem,UserMessage message,ServiceProcessor processor) {		
		return message.getGps();
	}
}
