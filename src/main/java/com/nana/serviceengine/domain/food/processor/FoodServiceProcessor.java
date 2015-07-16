package com.nana.serviceengine.domain.food.processor;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.flight.responsecreator.FlightSentenceCreator;
import com.nana.serviceengine.domain.food.bean.Foods;
import com.nana.serviceengine.domain.food.domainparam.FoodParam;
import com.nana.serviceengine.domain.food.responsecreator.FoodSentenceCreator;
import com.nana.serviceengine.domain.food.webapi.FoodAPI;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class FoodServiceProcessor extends ServiceProcessor {

	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
		FoodAPI foodAPI = FoodAPI.getinstance();
		List<Foods> foods = foodAPI.queryFoodsByPosition(domainParam);
		domainParam.setResult(foods);
		// send msg
		RobotResponser response = RobotResponser.getInstance();
		response.responseMessage(FoodSentenceCreator.getinstance()
				.createSentence(domainParam), mes);
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		RobotResponser.getInstance().responseMessage(
				FoodSentenceCreator.getinstance().createSentence(domainParam),
				mes);

	}

	@Override
	protected DomainParam createParam() {
		return new FoodParam() ;
	}

	@Override
	public String getDomainKeyWord() {
		return "food";
	}

}
