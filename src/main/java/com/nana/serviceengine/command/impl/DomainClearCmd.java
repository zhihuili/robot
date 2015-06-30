package com.nana.serviceengine.command.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.command.StateCmd;
import com.nana.serviceengine.dic.DomainDic;
import com.nana.serviceengine.statemachine.DialogState;

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
		try{
		
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		List<String[]> domainKeyWords = (List<String[]>) userDialog.getStateInfo().get("domainKeyWords");
		//如果用户给的概念较大，并且包含了多个概念
		if(domainKeyWords.size()>1){
			answer.append("我还无法处理大概念、多概念的问题");
		}else{
			String[] tmp = domainKeyWords.get(0);
			String parentValue = null;
			String notValue = null;
			//找父领域关键词，为进一步做概念澄清做准备
	    	for(int j=0;j<tmp.length;j++){
	    		if("not".equals(DomainDic.domainKeyWord.get(tmp[j]).getDomain())) notValue = "not";
	    		if("parent".equals(DomainDic.domainKeyWord.get(tmp[j]).getDomain())){
	    			parentValue = DomainDic.domainKeyWord.get(tmp[j]).getValue();
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
						goRecommand(userDialog,DomainDic.domainKeyWord.get(key).getDomain(),tmp,mes);
						return ;
					}else{
						preSelectDomain.add(DomainDic.domainKeyWord.get(key));
					}
				}
			}
			//如果子领域大于10个，就无法做领域提示了
			if(preSelectDomain.size()>10){
				answer.append("您给的概念太宽泛，我无法处理");
			}else{
				//开始概念澄清
				answer.append("请问您是要下面哪种服务呢？");
				StringBuilder display =new StringBuilder();
				for(int i=0;i<preSelectDomain.size();i++){
					display.append(preSelectDomain.get(i).getKeyWord()+" ");
				}
				
				
				
			}
			
			
		
	    
	    	
	    
		}
		}catch(Exception ex){
			
		}		
	}
	
	private void goRecommand(UserDialog userDialog,String aimDomain,String[] orginDomain,UserMessage mes){	
		for(int i=0;i<orginDomain.length;i++){
			if("parent".equals(orginDomain[i])){
				orginDomain[i] = aimDomain;
			}
		}
		userDialog.setState(DialogState.START);
		StartCmd.getInstance().doRun(mes, null);
	}
	
	private void goDomainClear(){
		
	}
	
}
