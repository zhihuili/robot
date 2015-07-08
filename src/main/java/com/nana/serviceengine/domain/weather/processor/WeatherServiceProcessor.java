package com.nana.serviceengine.domain.weather.processor;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.weather.bean.WeatherFuture;
import com.nana.serviceengine.domain.weather.domainparam.WeatherParam;
import com.nana.serviceengine.domain.weather.responsecreator.WeatherSentenceCreator;
import com.nana.serviceengine.domain.weather.webapi.WeatherAPI;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

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

	// private void finalDealRequest(UserMessage mes, WeatherParam wParam) {
	// // 如果使用spring 这里需要修改
	// SimpleApiAccessor apiAccessor = WeatherAPI.getInstance();
	//
	// String data = apiAccessor.loadData(wParam);
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("data", data);
	// params.put("date", wParam.getDate());
	// // TODO SEND MSG
	// RobotResponser.getInstance().responseMessage(
	// WeatherSentenceCreator.getInstance().createSentence(params),
	// mes);
	//
	// }

	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {

		WeatherAPI apiAccessor = WeatherAPI.getInstance();
		List<WeatherFuture> weatherFutures = apiAccessor.getWeatherData(domainParam);
		domainParam.setResult(weatherFutures);
		// TODO SEND MSG
		RobotResponser.getInstance().responseMessage(
						WeatherSentenceCreator.getInstance().createSentence(domainParam),
						mes);
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO SEND MSG
		RobotResponser.getInstance().responseMessage(
						WeatherSentenceCreator.getInstance().createSentence(domainParam),
						mes);
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
