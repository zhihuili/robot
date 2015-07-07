package com.nana.serviceengine.common.dic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.util.TxtReader;

public class DomainDic {
	public static final Map<String,DomainKeyWord> domainKeyWord = new HashMap<String,DomainKeyWord>();
	
	static {
		try{
			List<String> lines =new TxtReader("resources/slots").getContentLineList();
			for(String str:lines){
				String[] strs = str.split(" ");
				DomainKeyWord kdw =new DomainKeyWord();
				kdw.setKeyWord(strs[0]);
				kdw.setDomain(strs[1]);
				kdw.setValue(strs[2]);
				kdw.setParDomain(strs[3]);
				domainKeyWord.put(strs[0], kdw);
			}
		}catch(Exception ex){
			//TODO log
			ex.printStackTrace();
		}
	}
	

}
