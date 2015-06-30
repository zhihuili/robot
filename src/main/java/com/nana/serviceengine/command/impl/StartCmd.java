package com.nana.serviceengine.command.impl;


import java.util.Map;

import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.command.StateCmd;

public class StartCmd implements StateCmd {

	private static StartCmd sc = new StartCmd();
	
	private StartCmd(){}
	
	public static StartCmd getInstance(){
		return sc;
	}
	@Override
	public void doRun(UserMessage mes,Map<String,Object> moreInfo) {
		// TODO Auto-generated method stub
		
	}

}
