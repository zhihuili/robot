package com.nana.serviceengine.command.impl;


import java.util.Map;

import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.command.StateCmd;

public class FinishedCmd implements StateCmd {

private static FinishedCmd fc = new FinishedCmd();
	
	private FinishedCmd(){}
	
	public static FinishedCmd getInstance(){
		return fc;
	}
	
	@Override
	public void doRun(UserMessage mes,Map<String,Object> moreInfo) {
		// TODO Auto-generated method stub
		
	}

}
