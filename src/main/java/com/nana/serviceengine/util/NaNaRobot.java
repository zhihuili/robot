package com.nana.serviceengine.util;

import java.util.Date;

import com.nana.robot.api.context.ChartManager;
import com.nana.robot.chatterbean.util.Translate;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserAnswer;
import com.nana.serviceengine.inteceptor.MessageInteceptor;


public class NaNaRobot {

	public String getAnswer(UserMessage mes) {
		String task = null;
		if (mes != null) {
			task = MessageInteceptor.getInstance().themeIntecept1(mes);
		}

		if (task == null) {
			return "说点什么吧Y(^_^)Y";
		} else if (task.equals("servicetask")) {
			long start = new Date().getTime();
			String answer = null;
			while (new Date().getTime() - start < 10000) {

				answer = UserAnswer.Answers.get(mes.getUserid());
				if (answer != null) {
					UserAnswer.Answers.remove(mes.getUserid());
					return answer;
				}
			}
			return "您的问题太难了-_-# 让我想会，想好了我会告诉你的哦";
		} else if (task.equals("robottask")) {
			String output = Translate.translateString(mes.getMessage());
			return ChartManager.getInstance().response(output);
		}
		return "我还无法回答您的问题，下次我会努力的！";
	}
}
