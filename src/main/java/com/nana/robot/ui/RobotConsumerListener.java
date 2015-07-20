package com.nana.robot.ui;

import com.alibaba.fastjson.JSON;
import com.nana.common.message.RequestMessage;
import com.nana.common.message.ResponseMessage;
import com.nana.common.mq.ConsumerListener;
import com.nana.common.mq.MqFactory;
import com.nana.common.mq.MqProducer;
import com.nana.common.utils.Property;
import com.nana.serviceengine.common.bean.UserMessage;

public class RobotConsumerListener implements ConsumerListener {
	private NaNaRobot robot;
	private MqProducer appMqProducer = MqFactory.getMqProducer(Property
			.getInstance().getCfg("pid3"));
	private MqProducer csMqProducer = MqFactory.getMqProducer(Property
			.getInstance().getCfg("pid2"));

	private RobotConsumerListener() {
		robot = NaNaRobot.getInstance();
	}

	private static RobotConsumerListener instance = new RobotConsumerListener();

	public static RobotConsumerListener getInstance() {
		return instance;
	}

	@Override
	public void process(String key, String tag, byte[] body) {
		RequestMessage reqMessage = JSON.parseObject(new String(body),
				RequestMessage.class);
		System.out.println("user say" + reqMessage.getContent());
		UserMessage mes = new UserMessage();
		mes.setUserid(reqMessage.getId());
		mes.setMessage(reqMessage.getContent());
		mes.setReqMessage(reqMessage);
		robot.getAnswer(mes);
	}

	public void sendMsg(ResponseMessage resMessage, RequestMessage reqMessage) {
		System.out.println("robot:" + resMessage.getAudioText());
		System.out.println("display:" + resMessage.getDisplayText());

		if ("//TODO".equals(resMessage.getAudioText())) {
			sendToCS(reqMessage);					
		} else {
			sendToApp(resMessage, reqMessage);
		}
	}

	public void sendMsg(String mes, RequestMessage reqMessage) {
		System.out.println("robot:" + mes);

		if ("//TODO".equals(mes)) {
			sendToCS(reqMessage);
		} else {
			sendToApp(mes, reqMessage);
		}
	}

	/**
	 * robot 不能处理该消息，将消息发送到客服中心
	 * 
	 * @param reqMessage
	 */
	private void sendToCS(RequestMessage reqMessage) {
		csMqProducer.sendMessage(Property.getInstance().getCfg("topic2"), null, null,
				JSON.toJSONString(reqMessage).getBytes());
	}

	/**
	 * 发送robot结果到消息队列，等待webapi将其推送给App
	 * 
	 * @param responseStr
	 * @param reqMessage
	 */
	private void sendToApp(String responseStr, RequestMessage reqMessage) {
		ResponseMessage resMessage = new ResponseMessage();
		resMessage.setAudioText(responseStr);
		resMessage.setId(reqMessage.getId());
		resMessage.setMobileType(reqMessage.getMobileType());
		appMqProducer.sendMessage(Property.getInstance().getCfg("topic3"), null, null,
				JSON.toJSONString(resMessage).getBytes());
	}

	/**
	 * 发送robot结果到消息队列，等待webapi将其推送给App
	 * 
	 * @param responseStr
	 * @param reqMessage
	 */
	private void sendToApp(ResponseMessage response, RequestMessage reqMessage) {

		response.setId(reqMessage.getId());
		response.setMobileType(reqMessage.getMobileType());
		appMqProducer.sendMessage(Property.getInstance().getCfg("topic3"), null, null,
				JSON.toJSONString(response).getBytes());
	}

}
