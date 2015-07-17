package com.nana.serviceengine.domain.train.processor;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.train.bean.Train;
import com.nana.serviceengine.domain.train.daomainparam.TrainParam;
import com.nana.serviceengine.domain.train.responsecreator.TrainSentenceCreator;
import com.nana.serviceengine.domain.train.webapi.TrainAPI;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class TrainServiceProcessor extends ServiceProcessor{
	public TrainServiceProcessor(){
		
	}
	
	public TrainServiceProcessor(UserMessage mes){
		super(mes);
	}

	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO Auto-generated method stub
		TrainAPI trainAPI=TrainAPI.getInstance();
		List<Train> listTrain=trainAPI.getTrainData(domainParam);
		domainParam.setResult(listTrain);
		RobotResponser response = RobotResponser.getInstance();
		response.responseMessage(TrainSentenceCreator.getInstance()
				.createSentence(domainParam), mes);
		
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		// TODO Auto-generated method stub
		RobotResponser.getInstance().responseMessage(
				TrainSentenceCreator.getInstance().createSentence(domainParam),
				mes);
	}

	@Override
	protected DomainParam createParam() {
		// TODO Auto-generated method stub
		return new TrainParam();
	}

	@Override
	public String getDomainKeyWord() {
		// TODO Auto-generated method stub
		return "traintkt";
	}
	
	
}
