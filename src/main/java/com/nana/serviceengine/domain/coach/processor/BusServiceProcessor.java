package com.nana.serviceengine.domain.coach.processor;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.domain.coach.daomainparam.BusParam;
import com.nana.serviceengine.domain.coach.webapi.BusAPI;
import com.nana.serviceengine.domain.video.responsecreator.VideoSentenceCreator;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
/**
 * 处理领域需求
 * @author luhuanju
 */
public class BusServiceProcessor extends ServiceProcessor implements Runnable {
	
	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
	 BusAPI apiAccessor=BusAPI.getInstance();
	List<Bus> cityCoach=apiAccessor.getCityCoachinfo(domainParam);
	domainParam.setResult(cityCoach);
	
	RobotResponser.getInstance().responseMessage(VideoSentenceCreator.getInstance().createSentence(domainParam), mes);	
	
	
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		RobotResponser.getInstance().responseMessage(VideoSentenceCreator.getInstance().createSentence(domainParam), mes);		
	}

	@Override
	protected DomainParam createParam() {
		return new BusParam();
	}

	@Override
	public String getDomainKeyWord() {
		return "bus";
	}

}
