package com.nana.serviceengine.main;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.nana.common.message.RequestMessage;
import com.nana.common.message.ResponseMessage;
import com.nana.robot.ui.NaNaRobot;
import com.nana.serviceengine.common.bean.UserMessage;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		DateFormat df = new SimpleDateFormat("yyyy年MM月dd");
//		try {
//			System.out.println(df.parse((new Date().getYear()+1900)+"年"+"12月32")) ;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		RequestMessage reqMessage = new RequestMessage();
		while(true){
			Scanner scann = new Scanner(System.in);
		    String str= scann.next();
		    reqMessage.setId("wds");
		    reqMessage.setContent(str);
			NaNaRobot robot = NaNaRobot.getInstance();
			UserMessage mes = new UserMessage();
			mes.setUserid("wds");
			mes.setMessage(str);
			mes.setReqMessage(reqMessage);
			robot.getAnswer(mes);
		}
		

	}

}
