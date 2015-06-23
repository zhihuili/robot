package com.nana.serviceengine.processor.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.bean.WeatherParam;
import com.nana.serviceengine.cacher.UserAnswer;
import com.nana.serviceengine.cacher.UserTheme;
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
		String locKind = null;
		boolean isfinished = false;
		WeatherParam wParam = null;
		Date date = null;
		Date[] datetmp = TimeCollector.parseDate(mes.getMessage());
		if (datetmp != null && datetmp.length > 0) {
			date = datetmp[0];
		}
		if (UserTheme.UserDialog.get(mes.getUserid()) == null) {
			wParam = new WeatherParam();
			//如果为空默认为今天
			if (date == null)
				wParam.setDate(new Date());
			else
				wParam.setDate(date);
			if (mes.getLon() != null && mes.getLat() != null) {
				wParam.setGpslat(mes.getLat());
				wParam.setGpslot(mes.getLon());
				locKind = "gps";
				isfinished = true;
			} else {
				String[] location = CityCollector.parseCity(mes.getMessage());

				if (location != null && location.length > 0) {
					wParam.setLocation(location[0]);
					locKind = "cityname";
					isfinished = true;
				} else {
					unfinishedanswer = "请问您想知道什么地方的天气呢？";
				}
			}
			if (isfinished) {

				finalDealRequest(wParam);
			} else {
				udialog = new UserDialog();
				List<String> tmp = new ArrayList<String>();
				tmp.add("user " + mes.getMessage());
				tmp.add("robot " + unfinishedanswer);
				udialog.setCount(1);
				udialog.setDialog(tmp);
				udialog.setDomainObject(wParam);
				udialog.setProcessor(this);
				udialog.setUserid(mes.getUserid());
				UserTheme.UserDialog.put(mes.getUserid(), udialog);
				UserAnswer.Answers.put(mes.getUserid(), unfinishedanswer);
			}

		} else {
			udialog = UserTheme.UserDialog.get(mes.getUserid());
			wParam = (WeatherParam) udialog.getDomainObject();
			if (date == null)
				wParam.setDate(new Date());
			else
				wParam.setDate(date);
			wParam.getDate();
			if (wParam.getLocation() == null) {
				String[] location = CityCollector.parseCity(mes.getMessage());
				if (location != null && location.length > 0) {
					wParam.setLocation(location[0]);
					locKind = "cityname";
					isfinished = true;
				}
			}
			if (wParam.getGpslat() == null && wParam.getGpslot() == null) {
				if (mes.getLat() != null && mes.getLon() != null) {
					wParam.setGpslat(mes.getLat());
					wParam.setGpslot(mes.getLon());
					locKind = "gps";
					isfinished = true;
				}
			}
			if (isfinished) {
				finalDealRequest(wParam);
				// 移除之前可以进行一些数据保存工作
				UserTheme.UserDialog.remove(mes.getUserid());
			} else {
				unfinishedanswer = "我只是想知道您想要知道天气的地点，您就不能正面回答我的问题吗？";
				List<String> tmp = udialog.getDialog();
				tmp.add("user " + mes.getMessage());
				tmp.add("robot " + unfinishedanswer);
				udialog.setCount(udialog.getCount() + 1);
				udialog.setDialog(tmp);
				udialog.setDomainObject(wParam);
				udialog.setProcessor(this);
				udialog.setUserid(mes.getUserid());
				UserAnswer.Answers.put(mes.getUserid(), unfinishedanswer);

			}

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
