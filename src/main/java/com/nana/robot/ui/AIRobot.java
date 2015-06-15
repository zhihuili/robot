package com.nana.robot.ui;

import com.nana.common.mq.MqFactory;

public class AIRobot {
	//static method
	public static void main(String[] args) {
		startRobot();
	}
	
	//start a robot
	public static void startRobot() {
		MqFactory.startMqConsumer("CID_DEV_NANA_1", "DEV_NANA_1",
				RobotConsumerListener.getInstance());
	}

}
