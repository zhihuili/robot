package com.nana.robot.ui;

import com.nana.common.mq.ConsumerListener;
import com.nana.robot.api.context.ChartManager;
import com.nana.robot.chatterbean.util.Translate;

public class RobotConsumerListener implements ConsumerListener {
	private ChartManager chartManager;

	private RobotConsumerListener() {
		chartManager = ChartManager.getInstance();
	}

	private static RobotConsumerListener instance = new RobotConsumerListener();

	public static RobotConsumerListener getInstance() {
		return instance;
	}

	@Override
	public void process(String key, String tag, byte[] body) {
		String input = new String(body);
		System.out.println("user input: " + input);
		String tmp = Translate.translateString(input);
		String output = chartManager.response(tmp);
		System.out.println("robot answer: " + output);
		
	}

}
