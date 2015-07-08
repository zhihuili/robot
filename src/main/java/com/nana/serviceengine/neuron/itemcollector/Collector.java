package com.nana.serviceengine.neuron.itemcollector;


import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public interface Collector<T> {
	T getParam(UserMessage message,ServiceProcessor processor);
}
