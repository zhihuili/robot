package com.nana.serviceengine.statemachine.state.impl;


import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.statemachine.bean.DialogState;
import com.nana.serviceengine.statemachine.state.StateCmd;
import com.nana.serviceengine.statemachine.util.StateCommandUtil;

public class PreQuitCmd implements StateCmd {
	
	private static PreQuitCmd pqc = new PreQuitCmd();
	
	private PreQuitCmd(){}
	
	public static PreQuitCmd getInstance(){
		return pqc;
	}
	
	@Override
	public void doRun(UserMessage mes,Map<String,Object> moreInfo) {
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		List<Term> terms = mes.getTerms();
		String answer = "好的。";
		boolean hasYes = false;
		boolean hasNo = false;
		boolean quit = false;
		//寻找确认关键词
		for(int i=0;i<terms.size();i++){
			if("yes".equals(DomainDic.domainKeyWord.get(terms.get(i).getRealName()).getDomain())){
				hasYes = true;
			}
			if("not".equals(DomainDic.domainKeyWord.get(terms.get(i).getRealName()).getDomain())){
				hasNo = true;
			}
		}
		if(!hasYes && !hasNo){
			answer = "虽然我没有理解您的意思，但我就默认您回答了“否”！";
		}else if(hasYes && hasNo){
			quit = true;
			answer = "虽然我没有理解您的意思，但我就默认您回答了“是”！";
		}else if(hasYes && !hasNo){
			quit = true;
		}
		//如果用户选择了放弃，则释放相关数据
		if(quit){
			userDialog.setState(DialogState.START);
			userDialog.getResMessages().clear();
			userDialog.getStateInfo().clear();
			RobotResponser.getInstance().responseMessage(answer, mes);
		}else{
			StateCommandUtil.getInstance().cancelQuit(mes, userDialog); 
		}
		
		
	}

}
