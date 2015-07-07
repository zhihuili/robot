package com.nana.robot.ui;

import com.nana.robot.api.context.ChartManager;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.outin.robot.ChatRobotCenter;
import com.nana.serviceengine.statemachine.ActionSelector;
import com.nana.serviceengine.statemachine.state.StateCmd;

public class NaNaRobot {
	private static NaNaRobot robot = new NaNaRobot();

	private NaNaRobot() {
	}

	public static NaNaRobot getInstance() {
		return robot;
	}

	public void getAnswer(UserMessage userMessage) {
		try {

			StateCmd command = ActionSelector.getInstance().getCommand(
					userMessage);
			command.doRun(userMessage, null);
		} catch (Exception ex) {
			ex.printStackTrace();
			ChatRobotCenter.getInstance().getRobotResponse(userMessage);
		}

	}
}
