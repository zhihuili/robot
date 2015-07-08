package com.nana.serviceengine.domain.video.processor;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.dao.webapi.SimpleApiAccessor;
import com.nana.serviceengine.domain.video.bean.Movie;
import com.nana.serviceengine.domain.video.domainparam.VideoParam;
import com.nana.serviceengine.domain.video.responsecreator.VideoSentenceCreator;
import com.nana.serviceengine.domain.video.webapi.VideoAPI;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class VideoServiceProcessor extends ServiceProcessor {

	/**
	 * 数据收集完成或关键属性改变后，进行结果请求
	 */
	@Override
	protected void externalRequest(UserMessage mes, DomainParam domainParam) {
		SimpleApiAccessor apiAccessor = VideoAPI.getInstance();
		String data = apiAccessor.loadData(domainParam);
		List<Movie> movies = JSON.parseArray(data, Movie.class);
		//获得的结果要放入domainParam
		domainParam.setResult(movies);
		RobotResponser.getInstance().responseMessage(VideoSentenceCreator.getInstance().createSentence(domainParam), mes);		
	}

	@Override
	protected void internalRequest(UserMessage mes, DomainParam domainParam) {
		RobotResponser.getInstance().responseMessage(VideoSentenceCreator.getInstance().createSentence(domainParam), mes);				
	}

	@Override
	protected DomainParam createParam() {
		return new VideoParam();
	}

	@Override
	public String getDomainKeyWord() {
		return "video";
	}

}
