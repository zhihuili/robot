package com.nana.serviceengine.command.impl;


import java.util.Map;

import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.command.StateCmd;

public class PreQuitCmd implements StateCmd {
	
	private static PreQuitCmd pqc = new PreQuitCmd();
	
	private PreQuitCmd(){}
	
	public static PreQuitCmd getInstance(){
		return pqc;
	}
	
	@Override
	public void doRun(UserMessage mes,Map<String,Object> moreInfo) {
		// TODO Auto-generated method stub
		
	}

}
