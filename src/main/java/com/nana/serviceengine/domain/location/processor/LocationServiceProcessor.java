package com.nana.serviceengine.domain.location.processor;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.flight.responsecreator.FlightSentenceCreator;
import com.nana.serviceengine.domain.location.bean.Position;
import com.nana.serviceengine.domain.location.daomainparam.LocationParam;
import com.nana.serviceengine.domain.location.responsecreator.LocationSentenceCreator;
import com.nana.serviceengine.domain.location.webapi.LocationAPI;
import com.nana.serviceengine.domain.weather.responsecreator.WeatherSentenceCreator;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class LocationServiceProcessor extends ServiceProcessor {
	public LocationServiceProcessor() {

	}

	public LocationServiceProcessor(UserMessage mes) {
		super(mes);
	}

	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO Auto-generated method stub
		LocationAPI locationAPI=LocationAPI.getInstance();
		List<Position> listPosition=locationAPI.getPositionData(domainParam);
		domainParam.setResult(listPosition);
		RobotResponser response = RobotResponser.getInstance();
		response.responseMessage(LocationSentenceCreator.getInstance()
				.createSentence(domainParam), mes);

	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO Auto-generated method stub
		RobotResponser.getInstance().responseMessage(
				LocationSentenceCreator.getInstance().createSentence(domainParam),
				mes);
	}

	@Override
	protected DomainParam createParam() {
		// TODO Auto-generated method stub
		return new LocationParam();
	}

	@Override
	public String getDomainKeyWord() {
		// TODO Auto-generated method stub
		return "location";
	}

}
