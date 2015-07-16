package com.nana.serviceengine.domain.cookbook.processor;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.domain.coach.daomainparam.BusParam;
import com.nana.serviceengine.domain.coach.responsecreator.BusSentenceCreator;
import com.nana.serviceengine.domain.coach.webapi.BusAPI;
import com.nana.serviceengine.domain.cookbook.bean.Cookbook;
import com.nana.serviceengine.domain.cookbook.domainparam.CookbookParam;
import com.nana.serviceengine.domain.cookbook.responsecreator.CookbookSentenceCreator;
import com.nana.serviceengine.domain.cookbook.webapi.CookbookAPI;
import com.nana.serviceengine.domain.video.responsecreator.VideoSentenceCreator;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
/**
 * 处理领域需求
 * @author luhuanju
 */
public class CookbookServiceProcessor extends ServiceProcessor implements Runnable {
	
	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
	 CookbookAPI apiAccessor=CookbookAPI.getInstance();
	List<Cookbook> cookbook;
	try {
		cookbook = apiAccessor.getCityCoachinfo(domainParam);
		domainParam.setResult(cookbook);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	
	RobotResponser.getInstance().responseMessage(CookbookSentenceCreator.getInstance().createSentence(domainParam), mes);	
	
	
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		RobotResponser.getInstance().responseMessage(CookbookSentenceCreator.getInstance().createSentence(domainParam), mes);		
	}

	@Override
	protected DomainParam createParam() {
		return new CookbookParam();
	}
	@Override
	public String getDomainKeyWord() {
		return "cookbook";
	}

}
