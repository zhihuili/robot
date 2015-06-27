package com.nana.serviceengine.sentence.impl;

import java.util.Map;

import com.nana.serviceengine.sentence.SentenceCreator;

public class VideoSentenceCreator implements SentenceCreator {
	private static VideoSentenceCreator vsc = new VideoSentenceCreator();

	private VideoSentenceCreator() {

	}

	public static VideoSentenceCreator getInstance() {
		return vsc;
	}
	@Override
	public String createSentence(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return params.get("data").toString();
	}

}
