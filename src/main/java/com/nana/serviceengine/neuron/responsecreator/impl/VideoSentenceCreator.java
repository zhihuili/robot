package com.nana.serviceengine.neuron.responsecreator.impl;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.dao.webapi.bean.Movie;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.impl.VideoParam;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class VideoSentenceCreator implements SentenceCreator {
	private static VideoSentenceCreator vsc = new VideoSentenceCreator();

	private VideoSentenceCreator() {

	}

	public static VideoSentenceCreator getInstance() {
		return vsc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		VideoParam param = (VideoParam) params;
		ResponseMessageAdapter rpsMessage = new ResponseMessageAdapter();
		rpsMessage.setAudioText("好的。");
		Movie movie = param.getResMovie().get(param.getIndex()); 
		rpsMessage.setDisplayText(movie.getName());		
		return rpsMessage;
	}
	

}
