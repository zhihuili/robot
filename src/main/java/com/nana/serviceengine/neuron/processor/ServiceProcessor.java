package com.nana.serviceengine.neuron.processor;


import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.statemachine.bean.DialogState;
import com.nana.serviceengine.statemachine.bean.LoadType;
import com.nana.serviceengine.statemachine.bean.ParamState;

public abstract class ServiceProcessor implements Runnable{
	//用户消息
	protected UserMessage mes;
	
	public ServiceProcessor(){}
	
	public ServiceProcessor(UserMessage mes) {
    	this.mes = mes;
	}
	
	
    @Override
	public void run() {
    	UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		DomainParam domainParam = (DomainParam)userDialog.getParam();
		if (domainParam == null){
			domainParam = createParam();
			userDialog.setParam(domainParam);
		}
		String answer = "好的。";
		//第一次执行的时候需要收集参数信息
		if(domainParam.getLoadType() == null || LoadType.NOLOAD.equals(domainParam.getLoadType())){
			answer = domainParam.dataCollect(mes,this);		
		}
		//如果有数据请求
		if (!LoadType.NOLOAD.equals(domainParam.getLoadType())) {
			//如果是外部加载，则需要重新获取数据
			if (domainParam.getLoadType().equals(LoadType.EXTERNALLOAD)) {
				// 这里需要修改调用方法，正常返回的不需要存储
				domainParam.setCount(1);
				domainParam.setParamState(ParamState.DATAFINISH);
				externalRequest(mes,domainParam);
				
			} else if (domainParam.getLoadType().equals(LoadType.INTERNALLOAD)) {
				internalRequest(mes,domainParam);
			}
			//状态转移
			domainParam.setLoadType(LoadType.NOLOAD);			
			userDialog.setState(DialogState.FINISHED);
		} else {
			RobotResponser.getInstance().responseMessage(answer, mes);
			//进入等待状态
			domainParam.setParamState(ParamState.DATALACK);
			userDialog.setState(DialogState.WAIT);
		}

	}


    protected abstract void externalRequest(UserMessage mes,DomainParam domainParam);
    protected abstract void internalRequest(UserMessage mes,DomainParam domainParam);
    protected abstract DomainParam createParam();
    public abstract String getDomainKeyWord();
	
	
	public UserMessage getMes() {
		return mes;
	}

	public void setMes(UserMessage mes) {
		this.mes = mes;
	}
}
