package com.nana.serviceengine.neuron.domainparam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.bean.LoadType;
import com.nana.serviceengine.statemachine.bean.ParamItemState;
import com.nana.serviceengine.statemachine.bean.ParamState;

public abstract class DomainParam<T> {
	// 目前的状态 数据欠缺 ｜ 数据收集完成
	protected ParamState paramState;
	// 下面是否需要重新加载 1:整体数据直接加载 2:内部数据加载（类似换页）其它：不加载
	protected LoadType loadType;
	// 上一次是加载数据以来的第几次交互 每次进行加载时设为1
	protected int count;

	protected List<T> result;
	// 参数
	protected Map<String, ParamItem> params = new HashMap<String, ParamItem>();

	public String dataCollect(UserMessage mes,ServiceProcessor processor) {
		if(params.size() == 0) setParams();
		if (paramState == null || paramState.equals(ParamState.DATALACK)) {
			return dataLack(mes,processor);
		} else if (paramState.equals(ParamState.DATAFINISH)) {
			return dataCollectFinish(mes,processor);
		}
		return null;
	}

	/**
	 * 设置参数必须实现
	 */
	protected abstract void setParams();

	/**
	 * 再次进入领域时尝试进行属性收集
	 * 
	 * @param mes
	 * @return
	 */
	protected String dataCollectFinish(UserMessage mes,ServiceProcessor processor) {
		boolean isNeedExternal = false;
		boolean isNeedInternal = false;
		Iterator iterator = params.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ParamItem> item = (Entry<String, ParamItem>) iterator
					.next();
			ParamItem param = item.getValue();
			// 找关键词
			Object coRes = param.getCollector().getParam(param,mes,processor);
			// 如果找到的关键词为空，那么跳过
			if (coRes == null)
				continue;
			// 如果找到关键词不为空
			param.setCollectResult(coRes);
			Object newValue = param.getCmd().doProcess(param);
			// 如果得到的关键字和以前的相同则跳过
			if (newValue == null || param.getValue().equals(newValue))
				continue;
			param.setValue(newValue);
			
			// 有属性被改变
			if (param.isNeedExternalLoad()) {
				isNeedExternal = true;
			} else {
				isNeedInternal = true;
			}
		}
		// 这里换页优先于性质切换
		if (isNeedExternal) {
			loadType = LoadType.EXTERNALLOAD;
		}
		if (isNeedInternal) {
			loadType = LoadType.INTERNALLOAD;
		}
		return null;
	}

	/**
	 * 当第一次进入领域
	 * 
	 * @param mes
	 * @return
	 */
	protected String dataLack(UserMessage mes,ServiceProcessor processor) {
		String res = null;
		boolean isFinished = true;
		Iterator iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ParamItem> item = (Entry<String, ParamItem>) iterator.next();
			ParamItem param = item.getValue();
			// 找关键词
			Object coRes = param.getCollector().getParam(param,mes,processor);
			
			param.setCollectResult(coRes);
			Object newValue = param.getCmd().doProcess(param);
			// 没值的话
			if (newValue == null) {
				// 如果已经有值则跳过
				if (param.getValue() != null)
					continue;
				res = param.getAlertMes();
				isFinished = false;
				//第一次没有收集到信息则ParamItem的状态设置为Lack
				param.setParamItemState(ParamItemState.LACK);
				break;
			} else {
				param.setValue(newValue);
				//正确收集了信息之后则把ParamItem的状态设置为收集完成状态
				param.setParamItemState(ParamItemState.FINISH);
			}
		}
		if (isFinished) {
			loadType = LoadType.EXTERNALLOAD;
			return "好的。";
		} else {
			loadType = LoadType.NOLOAD;
			return res;
		}
	}

	/**
	 * 通过指定index来获取数据 index从0开始
	 * @param res 返回的结果存在这里
	 * @param index index
	 * @return 提示
	 */
	public String getResult(List<T> res,Integer index){
		if(result == null || result.size() == 0){
			return "唉～没找到数据。";
		}
		if(index == null){
			res.add(result.get(0));
			return "好的。";
		}
		if(index<0){
			return "上面没有了。";
		}
		if(index>result.size()){
			return "下面没有了。";
		}
		res.add(result.get(index-1));
		return "好的。";
	} 
	
	public List<T> getResult() {
		return result;
	}

	public Integer getResultIndex(){
		if(result != null) return result.size();
		return null;
	}
	
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

//	public List<T> getResult() {
//		return result;
//	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Map<String, ParamItem> getParams() {
		return params;
	}

	public void setParams(Map<String, ParamItem> params) {
		this.params = params;
	}

}
