package com.nana.serviceengine.neuron.itemcollector;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.bean.ParamItemState;

public abstract class Collector<T> {
	
	/**
	 * 根据状态选择具体的收集动作，不能重写
	 * @param paramItem
	 * @param message
	 * @param processor
	 * @return
	 */
	public final T getParam(ParamItem paramItem, UserMessage message,
			ServiceProcessor processor) {
		ParamItemState pis = paramItem.getParamItemState();
		switch (pis) {
		case INIT:{
			return initCollectParam(message, processor);
		}
		case LACK: {
			return lackCollectParam(message, processor);
		}
		case FINISH: {
			return finishCollectParam(paramItem,message, processor);
		}
		}
		return null;
	}

	/**
	 * 第一次进入领域时的收集动作
	 * @param message
	 * @param processor
	 * @return
	 */
	public abstract T initCollectParam(UserMessage message, ServiceProcessor processor);

	/**
	 * 第一次缺少数据，第二次以及以后收集的动作
	 * @param message
	 * @param processor
	 * @return
	 */
	public abstract T lackCollectParam(UserMessage message, ServiceProcessor processor);

	/**
	 * 已有数据，但是用户提出的修改时的收集动作
	 * @param message
	 * @param processor
	 * @return
	 */
	public abstract T finishCollectParam(ParamItem paramItem,UserMessage message,
			ServiceProcessor processor);
}
