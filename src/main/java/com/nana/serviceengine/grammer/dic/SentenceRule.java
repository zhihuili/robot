package com.nana.serviceengine.grammer.dic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.util.TxtReader;
import com.nana.serviceengine.grammer.bean.SentenceRuleItem;

public class SentenceRule {
	public static Map<String,SentenceRuleItem> SentenceRuleModel = new LinkedHashMap<String,SentenceRuleItem>();
	static {
		try{
			List<String> lines = new TxtReader("resources/sentencerule").getContentLineList();
			for(String str:lines){
				String[] tmp= str.split(" ");
				SentenceRuleModel.put(tmp[0], new SentenceRuleItem(tmp[0],tmp[1],Integer.parseInt(tmp[2]),Integer.parseInt(tmp[3]),tmp[4]));
			}
			
		}catch(Exception ex){
			//TODO log
			ex.printStackTrace();
		}
		
		
	}
}
