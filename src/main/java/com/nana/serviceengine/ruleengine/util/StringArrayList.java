package com.nana.serviceengine.ruleengine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringArrayList {
	
	public static List<String> parseString(String data,String regex){
		List<String> res = null;
		try{
			String[] tmp = data.split(regex);
		    List<String> tmp1 = Arrays.asList(tmp);
		    res = new ArrayList<String>(tmp1);
		}catch(Exception ex){
			//TODO log
		}
		return res;
	}
}
