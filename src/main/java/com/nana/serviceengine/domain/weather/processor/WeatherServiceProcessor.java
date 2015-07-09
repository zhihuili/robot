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
