package com.nana.serviceengine.outin.robot;

import com.nana.robot.api.context.ChartManager;
import com.nana.serviceengine.common.bean.UserMessage;

public class ChatRobotCenter {
	private static ChatRobotCenter rc = new ChatRobotCenter();
	private ChatRobotCenter(){}
	
	public static ChatRobotCenter getInstance(){
		return rc;
	}
	
	public String getRobotResponse(String input){
		return ChartManager.getInstance().response(input); 
	}
	
	public String getRobotResponse(UserMessage mes){
		return getRobotResponse(mes.getMessage());
	}
}
