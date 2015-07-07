package com.nana.serviceengine.statemachine.state.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.statemachine.bean.DialogState;
import com.nana.serviceengine.statemachine.state.StateCmd;
import com.nana.serviceengine.statemachine.util.StateCommandUtil;

/**
 * 领域澄清，代价有点高啊
 * @author wds
 *
 */
public class DomainClearCmd implements StateCmd {
	private static DomainClearCmd dcc = new DomainClearCmd();
	
	private DomainClearCmd(){}
	
	public static DomainClearCmd getInstance(){
		return dcc;
	}
	
	@Override
	public void doRun(UserMessage mes,Map<String,Object> moreInfo) {
		StringBuilder answer = new StringBuilder();
		StringBuilder display =new StringBuilder();
		try{
		
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		List<String[]> domainKeyWords = mes.getDomainKeyWords();
		//如果用户给的概念较大，并且包含了多个概念
		if(domainKeyWords.size()>1){
			answer.append("我还无法处理大概念且多概念的问题");
		}else{
			Integer index = 0;
			String parentValue = null;
			String notValue = null;
			//找父领域关键词，为进一步做概念澄清做准备
	    	for(int j=0;j<mes.getTerms().size();j++){
	    		String tmp  = mes.getTerms().get(j).getRealName();
	    		if("not".equals(DomainDic.domainKeyWord.get(tmp).getDomain())) notValue = "not";
	    		if("parent".equals(DomainDic.domainKeyWord.get(tmp).getDomain())){
	    			parentValue = DomainDic.domainKeyWord.get(tmp).getValue();
	    			index = j;
	    			break;
	    		}
	    	}	
	    	
			List<DomainKeyWord> preSelectDomain = new ArrayList<DomainKeyWord>();
			Iterator it = DomainDic.domainKeyWord.keySet().iterator();
			//寻找子领域关键词，准备概念澄清
			while(it.hasNext()){
				String key = (String) it.next();
				if(parentValue.equals(DomainDic.domainKeyWord.get(key).getParDomain())){
					if(notValue != null){
						//如果有否定词则选择第一个子领域关键词进入推荐
						StateCommandUtil.getInstance().goRecommand(userDialog, key, index, mes);
						return ;
					}else{
						preSelectDomain.add(DomainDic.domainKeyWord.get(key));
					}
				}
			}
			//如果子领域大于10个，就无法做领域提示了
			if(preSelectDomain.size()>10){
				answer.append("您给的概念太宽泛，我无法处理。");
				userDialog.setState(DialogState.START);
			}else{
				//开始概念澄清
				answer.append("请问您是要下面哪种服务呢？");	
				for(int i=0;i<preSelectDomain.size();i++){
					display.append(preSelectDomain.get(i).getKeyWord()+" ");
				}
				userDialog.getStateInfo().clear();
				userDialog.getStateInfo().put("terms", mes.getTerms());
				userDialog.getStateInfo().put("index", index);
				userDialog.getStateInfo().put("parentvalue", parentValue);
				userDialog.getStateInfo().put("originmessage", mes.getMessage());
				//userDialog.getStateInfo().put("selectdomain", preSelectDomain);
				userDialog.setState(DialogState.RESELECT);
				
				
				
			}
			//构造消息并发送
			ResponseMessageAdapter rpMessage = new ResponseMessageAdapter();
			userDialog.getDialog().add(mes.getMessage());
			userDialog.getDialog().add(answer.toString());
			rpMessage.setAudioText(answer.toString());
			rpMessage.setDisplayText(display.toString());
			userDialog.getResMessages().add(rpMessage);
			RobotResponser.getInstance().responseMessage(rpMessage, mes);
	   
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
}
