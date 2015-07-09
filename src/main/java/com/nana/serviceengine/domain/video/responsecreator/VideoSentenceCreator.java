package com.nana.serviceengine.domain.video.responsecreator;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.common.dic.HtmlDic;
import com.nana.serviceengine.domain.video.bean.Movie;
import com.nana.serviceengine.domain.video.bean.MovieDisplay;
import com.nana.serviceengine.domain.video.domainparam.VideoParam;
import com.nana.serviceengine.inout.bean.ResponseDisplay;
import com.nana.serviceengine.inout.util.HtmlDataFiller;
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
		
		Integer index = (Integer) param.getParams()
				.get("indexChange").getValue();
		// 获取结果
		String alert = param.getResult(movieList, index);
		// 设置提示的话语
		rpsMessage.setAudioText(alert);
		
		
		//构造显示在App段的消息
		if (movieList.size() != 0 && index<=params.getResult().size() && index>0){
	    	ResponseDisplay responseDisplay = new ResponseDisplay();
	    	//构造显示的bean
	    	MovieDisplay md = new MovieDisplay();
	    	md.setMovie(movieList.get(0));
	    	responseDisplay.setDataType("1");
	    	responseDisplay.setHeight("0.3");
	    	responseDisplay.setContent(HtmlDataFiller.getInstance().fillHtml("videohtml", md));
			
	    	rpsMessage.setDisplayText(JSON.toJSONString(responseDisplay));
	    }
		return rpsMessage;
	}

}
