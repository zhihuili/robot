package com.nana.serviceengine.util;

import java.util.Scanner;

import com.nana.serviceengine.bean.UserMessage;

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
		while(true){
			Scanner scann = new Scanner(System.in);
		    String str= scann.next();
			NaNaRobot robot = new NaNaRobot();
			UserMessage mes = new UserMessage();
			mes.setUserid("wds");
			mes.setMessage(str);
			System.out.println(robot.getAnswer(mes));
			
		}

	}

}
