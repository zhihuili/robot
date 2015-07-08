package com.nana.serviceengine.domain.video.responsecreator;

import java.util.ArrayList;
import java.util.List;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.video.bean.Movie;
import com.nana.serviceengine.domain.video.domainparam.VideoParam;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
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
		// 构造返回消息
		ResponseMessageAdapter rpsMessage = new ResponseMessageAdapter();
		// 存储返回的结果
		List<Movie> movieList = new ArrayList<Movie>();
		// 获取结果
		String alert = param.getResult(movieList, (Integer) param.getParams()
				.get("indexChange").getValue());
		// 设置提示的话语
		rpsMessage.setAudioText(alert);
		//构造显示在App段的消息
		if (movieList.size() != 0)
			rpsMessage.setDisplayText(movieList.get(0).getName());
		return rpsMessage;
	}

}
