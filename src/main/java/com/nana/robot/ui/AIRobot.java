package com.nana.robot.ui;

import com.nana.common.utils.Property;

import com.nana.common.mq.MqFactory;

public class AIRobot {
	//static method
	public static void main(String[] args) {
		startRobot();
	}
	
	//start a robot
	public static void startRobot() {
		MqFactory.startMqConsumer(Property.getInstance().getCfg("cid1"), Property.getInstance().getCfg("topic1"),
				RobotConsumerListener.getInstance());
	}

}
