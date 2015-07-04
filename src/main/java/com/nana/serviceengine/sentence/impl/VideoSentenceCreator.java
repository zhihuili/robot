package com.nana.serviceengine.sentence.impl;

import com.nana.common.message.ResponseMessage;
import com.nana.serviceengine.dao.bean.DomainParam;
import com.nana.serviceengine.dao.bean.impl.VideoParam;
import com.nana.serviceengine.sentence.SentenceCreator;
import com.nana.serviceengine.webapi.bean.Movie;

public class VideoSentenceCreator implements SentenceCreator {
	private static VideoSentenceCreator vsc = new VideoSentenceCreator();

	private VideoSentenceCreator() {

	}

	public static VideoSentenceCreator getInstance() {
		return vsc;
	}

	@Override
	public ResponseMessage createSentence(DomainParam params) {
		VideoParam param = (VideoParam) params;
		ResponseMessage rpsMessage = new ResponseMessage();
		rpsMessage.setAudioText("好的。");
		
		Movie movie = param.getResMovie().get(param.getIndex()); 
		
		rpsMessage.setDisplayText(movie.getName());
		
		
		return rpsMessage;
	}
	

}
