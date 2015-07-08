package com.nana.serviceengine.neuron.domainparam.bean;

import com.nana.serviceengine.neuron.itemcollector.Collector;

public class ParamItem<T,V> {
	//参数名称
	private String name;
	//参数值
	private T value;
	//收集器的Class
	private Collector collector;
	//收集器的结果
	private V collectResult;
	//如果参数缺少则提示的话语
	private String alertMes;
	//是否需要加载新的外部数据
	private boolean isNeedExternalLoad;
	//当获取到消息以后处理办法
	private ParamCommand cmd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	public Collector getCollector() {
		return collector;
	}
	public void setCollector(Collector collector) {
		this.collector = collector;
	}
	public V getCollectResult() {
		return collectResult;
	}
	public void setCollectResult(V collectResult) {
		this.collectResult = collectResult;
	}
	public String getAlertMes() {
		return alertMes;
	}
	public void setAlertMes(String alertMes) {
		this.alertMes = alertMes;
	}
	public ParamCommand getCmd() {
		return cmd;
	}
	public void setCmd(ParamCommand cmd) {
		this.cmd = cmd;
	}
	public boolean isNeedExternalLoad() {
		return isNeedExternalLoad;
	}
	public void setNeedExternalLoad(boolean isNeedExternalLoad) {
		this.isNeedExternalLoad = isNeedExternalLoad;
	}
	
	
	
}
