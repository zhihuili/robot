package com.nana.serviceengine.main;


import java.util.Scanner;

import com.nana.common.message.RequestMessage;
import com.nana.robot.ui.NaNaRobot;
import com.nana.serviceengine.common.bean.UserMessage;
public class Test {

	public static void main(String[] args) {
		RequestMessage reqMessage = new RequestMessage();
		while(true){
			Scanner scann = new Scanner(System.in);
		    String str= scann.next();
		    reqMessage.setId("2545535b30537ba130b673750d38dcb12485b7aa56a3845c7fc796b36187fb46");
		    reqMessage.setContent(str);
			NaNaRobot robot = NaNaRobot.getInstance();
			UserMessage mes = new UserMessage();
			mes.setUserid("2545535b30537ba130b673750d38dcb12485b7aa56a3845c7fc796b36187fb46");
			mes.setMessage(str);
			mes.setReqMessage(reqMessage);
			robot.getAnswer(mes);
		}
	}
}
