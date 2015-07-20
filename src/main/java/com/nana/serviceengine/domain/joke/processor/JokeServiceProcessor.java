package com.nana.serviceengine.domain.joke.processor;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.domain.coach.daomainparam.BusParam;
import com.nana.serviceengine.domain.coach.webapi.BusAPI;
import com.nana.serviceengine.domain.joke.bean.Joke;
import com.nana.serviceengine.domain.joke.daomainparam.JokeParam;
import com.nana.serviceengine.domain.joke.responsecreator.JokeSentenceCreator;
import com.nana.serviceengine.domain.joke.webapi.JokeAPI;
import com.nana.serviceengine.domain.video.responsecreator.VideoSentenceCreator;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
/**
 * 处理领域需求
 * @author luhuanju
 */
public class JokeServiceProcessor extends ServiceProcessor implements Runnable  {
	
	@Override
	protected void externalRequest (UserMessage mes, DomainParam domainParam) {
	 JokeAPI apiAccessor=JokeAPI.getInstance();
	List<Joke> joke;
		try {
			joke = apiAccessor.getJoke();
			domainParam.setResult(joke);
			RobotResponser.getInstance().responseMessage(JokeSentenceCreator.getInstance().createSentence(domainParam), mes);	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		RobotResponser.getInstance().responseMessage(JokeSentenceCreator.getInstance().createSentence(domainParam), mes);		
	}

	@Override
	protected DomainParam createParam() {
		return new JokeParam();
	}

	@Override
	public String getDomainKeyWord() {
		return "joke";
	}

}
