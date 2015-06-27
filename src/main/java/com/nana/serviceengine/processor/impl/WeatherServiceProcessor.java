package com.nana.serviceengine.processor.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;

import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.bean.WeatherParam;
import com.nana.serviceengine.cacher.UserAnswer;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.dic.DomainDic;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.sentence.impl.WeatherSentenceCreator;
import com.nana.serviceengine.util.CityCollector;
import com.nana.serviceengine.util.TimeCollector;
import com.nana.serviceengine.webapi.APIAccessor;
import com.nana.serviceengine.webapi.impl.WeatherAPI;

public class WeatherServiceProcessor extends ServiceProcessor {

	public WeatherServiceProcessor() {

	}

	public WeatherServiceProcessor(UserMessage mes) {
		super(mes);
	}

	@Override
	public void run() {
		
		UserDialog udialog = null;
		String unfinishedanswer = null;
		//List<String> locations = new ArrayList<String>();
		boolean isfinished = false;
		WeatherParam wParam = domainObject == null ? new WeatherParam():(WeatherParam)domainObject;
		if(wParam.getDate() == null){
			Date[] datetmp = TimeCollector.parseDate(mes);
			if (datetmp != null && datetmp.length > 0) {
				wParam.setDate(datetmp[0]);
			}else
				//如果时间为空，默认为今天
				wParam.setDate(new Date());
		}
		
		if(mes.getLat() != null && mes.getLon() != null){
			wParam.setGpslat(mes.getLat());
			wParam.setGpslon(mes.getLon());
		}else if(wParam.getLocation() == null){
			for(Term term:mes.getTerms()){
				DomainKeyWord keyWord = DomainDic.domainKeyWord.get(term.getRealName());
				if(keyWord != null && "address".equals(keyWord.getDomain())){
					//locations.add(keyWord.getKeyWord());
					//默认取第一个地点
					wParam.setLocation(keyWord.getKeyWord());
					break;
				}
			}
		}
		//验证参数 不同的领域验证不一样
		if(wParam.getGpslat() == null || wParam.getLocation() == null){		
			unfinishedanswer = "请问您想知道什么地方的天气？";
		}else{
			isfinished = true;
		}
		if (isfinished) {
			finalDealRequest(wParam);
		} else {
			int count = udialog.getCount() + 1;
			udialog.setCount(count);
			sendMsg(unfinishedanswer);
		}

	}

	private void finalDealRequest(WeatherParam wParam){
		//如果使用spring 这里需要修改
		APIAccessor apiAccessor = WeatherAPI.getInstance();
		
		String data = apiAccessor.getInfo(wParam);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("data", data);
		params.put("date", wParam.getDate());
		UserAnswer.Answers.put(mes.getUserid(), WeatherSentenceCreator
				.getInstance().createSentence(params));

	}
}
