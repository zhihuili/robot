package com.nana.serviceengine.processor.impl;

import java.util.HashMap;
import java.util.Map;

import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.dao.bean.DomainParam;
import com.nana.serviceengine.dao.bean.impl.WeatherParam;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.sentence.impl.WeatherSentenceCreator;
import com.nana.serviceengine.statemachine.DialogState;
import com.nana.serviceengine.statemachine.bean.LoadType;
import com.nana.serviceengine.statemachine.bean.ParamState;
import com.nana.serviceengine.webapi.SimpleApiAccessor;
import com.nana.serviceengine.webapi.impl.WeatherAPI;

public class WeatherServiceProcessor extends ServiceProcessor {

	public WeatherServiceProcessor() {

	}

	public WeatherServiceProcessor(UserMessage mes) {
		super(mes);
	}

	@Override
	public void run() {

		UserDialog udialog = UserTheme.UserDialog.get(mes.getUserid());
		DomainParam domainParam = (DomainParam) udialog.getParam();
		if (domainParam == null) {
			domainParam = new WeatherParam();
			udialog.setParam(domainParam);
		}
		String answer = "好的。";
		if (domainParam.getLoadType() == null
				|| LoadType.NOLOAD.equals(domainParam.getLoadType())) {
			answer = domainParam.dataCollect(mes);
		}
		// 返回的状态不为null则进行数据获取
		if (domainParam.getLoadType() != null
				&& !LoadType.NOLOAD.equals(domainParam.getLoadType())) {

			// 因为这里没有换页的问题所以不存在内部数据切换问题
			if (LoadType.EXTERNALLOAD.equals(domainParam.getLoadType())) {
				finalDealRequest(mes, (WeatherParam) domainParam);
			}
			domainParam.setLoadType(LoadType.NOLOAD);
			domainParam.setParamState(ParamState.DATAFINISH);
			// 保存会话的参数
			udialog.setState(DialogState.FINISHED);
		} else {
			domainParam.setParamState(ParamState.DATALACK);
			udialog.setState(DialogState.WAIT);
			RobotConsumerListener.getInstance().sendMsg(answer,
					mes.getReqMessage());
		}
	}

	private void finalDealRequest(UserMessage mes, WeatherParam wParam) {
		// 如果使用spring 这里需要修改
		SimpleApiAccessor apiAccessor = WeatherAPI.getInstance();
		
		String data = apiAccessor.loadData(wParam);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", data);
		params.put("date", wParam.getDate());
		// TODO SEND MSG
		RobotConsumerListener.getInstance().sendMsg(WeatherSentenceCreator.getInstance()
				.createSentence(params), mes.getReqMessage());
		//sendMsg(userDialog, );
		// UserAnswer.Answers.put(mes.getUserid(), );

	}
}
