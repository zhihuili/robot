package com.nana.robot.ui;

import com.alibaba.fastjson.JSON;
import com.nana.common.message.RequestMessage;
import com.nana.common.message.ResponseMessage;
import com.nana.common.mq.ConsumerListener;
import com.nana.common.mq.MqFactory;
import com.nana.common.mq.MqProducer;
import com.nana.robot.api.context.ChartManager;
import com.nana.robot.chatterbean.util.Translate;

public class RobotConsumerListener implements ConsumerListener {
	private ChartManager chartManager;
	private MqProducer appMqProducer = MqFactory
			.getMqProducer("PID_DEV_NANA_3");

	private RobotConsumerListener() {
		chartManager = ChartManager.getInstance();
	}

	private static RobotConsumerListener instance = new RobotConsumerListener();

	public static RobotConsumerListener getInstance() {
		return instance;
	}

	@Override
	public void process(String key, String tag, byte[] body) {
		RequestMessage reqMessage = JSON.parseObject(new String(body),
				RequestMessage.class);
		String input = reqMessage.getContent();
		System.out.println("user input: " + input);
		String tmp = Translate.translateString(input);
		String output = chartManager.response(tmp);
		System.out.println("robot answer: " + output);
		sendToApp(output, reqMessage);
	}

	/**
	 * 发送robot结果到消息队列，等待webapi将其推送给App
	 * 
	 * @param responseStr
	 * @param reqMessage
	 */
	private void sendToApp(String responseStr, RequestMessage reqMessage) {
		ResponseMessage resMessage = new ResponseMessage();
		resMessage.setDisplayText(responseStr);
		resMessage.setId(reqMessage.getId());
		resMessage.setMobileType(reqMessage.getMobileType());
		appMqProducer.sendMessage("DEV_NANA_3", null, null,
				JSON.toJSONString(resMessage).getBytes());
	}

}
