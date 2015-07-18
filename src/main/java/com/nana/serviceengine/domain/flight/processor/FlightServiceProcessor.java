package com.nana.serviceengine.domain.flight.processor;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.domain.flight.domainparam.FlightParam;
import com.nana.serviceengine.domain.flight.responsecreator.FlightSentenceCreator;
import com.nana.serviceengine.domain.flight.webapi.FlightAPI;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class FlightServiceProcessor extends ServiceProcessor {

	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO Auto-generated method stub
		FlightAPI flightApi = FlightAPI.getinstance();
		List<Flight> flights = flightApi.searchFligthResult(domainParam);
		domainParam.setResult(flights);
		// send msg
		RobotResponser response = RobotResponser.getInstance();
		response.responseMessage(FlightSentenceCreator.getinstance()
				.createSentence(domainParam), mes);
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO Auto-generated method stub
		RobotResponser.getInstance()
				.responseMessage(
						FlightSentenceCreator.getinstance().createSentence(
								domainParam), mes);
	}

	@Override
	protected DomainParam createParam() {
		// TODO Auto-generated method stub
		return new FlightParam();
	}

	@Override
	public String getDomainKeyWord() {
		// TODO Auto-generated method stub
		return "planeticket";
	}

}
