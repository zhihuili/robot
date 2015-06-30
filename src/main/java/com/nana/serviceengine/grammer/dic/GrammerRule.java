package com.nana.serviceengine.grammer.dic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.util.TxtReader;

public class GrammerRule {
	public static Map<String,String> GramModel = new HashMap<String,String>();
	static {
		try{
			List<String> lines = new TxtReader("resources/syntaxrule").getContentLineList();
			for(String str:lines){
				String[] tmp= str.split(" ");
				GramModel.put(tmp[0], tmp[1]);
			}
			
		}catch(Exception ex){
			//TODO log
			ex.printStackTrace();
		}
		
		
	}
}
