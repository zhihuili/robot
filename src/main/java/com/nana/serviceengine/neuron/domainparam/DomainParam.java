package com.nana.serviceengine.neuron.domainparam;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.statemachine.bean.LoadType;
import com.nana.serviceengine.statemachine.bean.ParamState;

public abstract class DomainParam {
	//目前的状态 数据欠缺 ｜ 数据收集完成
	protected ParamState paramState;
	//下面是否需要重新加载 1:整体数据直接加载 2:内部数据加载（类似换页）其它：不加载
	protected LoadType loadType;
	//上一次是加载数据以来的第几次交互 每次进行加载时设为1
	protected int count;
	
	public String dataCollect(UserMessage mes){
		if (paramState == null) {
			return dataLack(mes);
		} else if (paramState.equals(ParamState.DATAFINISH)) {
			return dataCollectFinish(mes);
		} else if (paramState.equals(ParamState.DATALACK)) {
			return dataLack(mes);
		} else if (paramState.equals(ParamState.DATAUPDATE)) {
			return dataUpdate(mes);
		}
		return null;
	}
	protected abstract String dataCollectFinish(UserMessage mes);
	protected abstract String dataLack(UserMessage mes);
	protected abstract String dataUpdate(UserMessage mes);
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ParamState getParamState() {
		return paramState;
	}
	public void setParamState(ParamState paramState) {
		this.paramState = paramState;
	}
	public LoadType getLoadType() {
		return loadType;
	}
	public void setLoadType(LoadType loadType) {
		this.loadType = loadType;
	}

	
	
	
	
}
