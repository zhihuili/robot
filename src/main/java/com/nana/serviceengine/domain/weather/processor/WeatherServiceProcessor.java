package com.nana.serviceengine.domain.weather.processor;

import java.util.HashMap;
import java.util.Map;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.dao.webapi.SimpleApiAccessor;
import com.nana.serviceengine.domain.weather.domainparam.WeatherParam;
import com.nana.serviceengine.domain.weather.responsecreator.WeatherSentenceCreator;
import com.nana.serviceengine.domain.weather.webapi.WeatherAPI;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.bean.DialogState;
import com.nana.serviceengine.statemachine.bean.LoadType;
import com.nana.serviceengine.statemachine.bean.ParamState;

public class WeatherServiceProcessor extends ServiceProcessor {

	public WeatherServiceProcessor() {

	}

	public WeatherServiceProcessor(UserMessage mes) {
		super(mes);
	}

	// @Override
	// public void run() {
	//
	// UserDialog udialog = UserTheme.UserDialog.get(mes.getUserid());
	// DomainParam domainParam = (DomainParam) udialog.getParam();
	// if (domainParam == null) {
	// domainParam = new WeatherParam();
	// udialog.setParam(domainParam);
	// }
	// String answer = "好的。";
	// if (domainParam.getLoadType() == null
	// || LoadType.NOLOAD.equals(domainParam.getLoadType())) {
	// answer = domainParam.dataCollect(mes);
	// }
	// // 返回的状态不为null则进行数据获取
	// if (domainParam.getLoadType() != null
	// && !LoadType.NOLOAD.equals(domainParam.getLoadType())) {
	//
	// // 因为这里没有换页的问题所以不存在内部数据切换问题
	// if (LoadType.EXTERNALLOAD.equals(domainParam.getLoadType())) {
	// finalDealRequest(mes, (WeatherParam) domainParam);
	// }
	// domainParam.setLoadType(LoadType.NOLOAD);
	// domainParam.setParamState(ParamState.DATAFINISH);
	// // 保存会话的参数
	// udialog.setState(DialogState.FINISHED);
	// } else {
	// domainParam.setParamState(ParamState.DATALACK);
	// udialog.setState(DialogState.WAIT);
	// RobotResponser.getInstance().responseMessage(answer, mes);
	// }
	// }

	private void finalDealRequest(UserMessage mes, WeatherParam wParam) {
		// 如果使用spring 这里需要修改
		SimpleApiAccessor apiAccessor = WeatherAPI.getInstance();

		String data = apiAccessor.loadData(wParam);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", data);
		params.put("date", wParam.getDate());
		// TODO SEND MSG
		RobotResponser.getInstance().responseMessage(
				WeatherSentenceCreator.getInstance().createSentence(params),
				mes);

	}

	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
		// 如果使用spring 这里需要修改
				SimpleApiAccessor apiAccessor = WeatherAPI.getInstance();
				String data = apiAccessor.loadData(domainParam);
				
				// TODO SEND MSG
				RobotResponser.getInstance().responseMessage(
						WeatherSentenceCreator.getInstance().createSentence(params),
						mes);
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		externalRequest(mes,domainParam);
	}

	@Override
	protected DomainParam createParam() {
		return new  WeatherParam();
	}

	@Override
	public String getDomainKeyWord() {
		return "weather";
	}
}
