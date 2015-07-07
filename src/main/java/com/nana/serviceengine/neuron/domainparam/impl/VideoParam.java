package com.nana.serviceengine.neuron.domainparam.impl;

import java.util.List;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.dao.webapi.bean.Movie;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.itemcollector.AttriCollector;
import com.nana.serviceengine.neuron.processor.itemcollector.PageTurnCollector;
import com.nana.serviceengine.statemachine.bean.LoadType;

/**
 * 电影搜索参数
 * 
 * @author wds
 *
 */
public class VideoParam extends DomainParam {

	private String keyWord;
	private Integer index = 1;
	private List<Movie> resMovie;

	@Override
	public String dataCollectFinish(UserMessage mes) {
		Integer indexChange = PageTurnCollector.getInstance().getIndexChange(
				mes);
		String attri = AttriCollector.getInstance().deleteDomaintoGetAttri(mes, "video");
		StringBuilder builder = new StringBuilder();
		if(indexChange != null){
			index += indexChange;
			if(index < 1){
				builder.append("上面没有啦!");
				index = 1;
			}else if(index>resMovie.size()){
				builder.append("下面没有啦!");
			}
			else{
				builder.append("好的。");
				loadType = LoadType.INTERNALLOAD;
			}
		}
		if(!"".equals(attri)){
			builder.append("已为您查找新电影");
		    keyWord  = attri;
		    index = 1;
			loadType = LoadType.EXTERNALLOAD;
		}
	
		return builder.toString();
	}

	@Override
	public String dataLack(UserMessage mes) {
		
		String attri = null;
		if(count == 0){
			attri = AttriCollector.getInstance().getAttributive(mes,"video");
		}else{
			attri = AttriCollector.getInstance().getAttributive(mes);
		}
		count++;
		if("".equals(attri)){
			loadType = LoadType.NOLOAD;
			return "请问您喜欢看什么电影，说几个关键字就行。";
		}else{
			keyWord = attri;
			loadType = LoadType.EXTERNALLOAD;
		}
		return "好的。";
	}

	@Override
	public String dataUpdate(UserMessage mes) {
		
		return null;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Movie> getResMovie() {
		return resMovie;
	}

	public void setResMovie(List<Movie> resMovie) {
		this.resMovie = resMovie;
	}

	
}
