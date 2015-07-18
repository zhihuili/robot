package com.nana.serviceengine.neuron.itemcollector;


import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public interface Collector<T> {
	T getParam(ParamItem paramItem,UserMessage message,ServiceProcessor processor);
}
