package com.nana.serviceengine.dic.pool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.util.ConfigCenter;
import com.nana.serviceengine.util.TxtReader;

public class ChinaCity {
	public static Map<String, String> CityMap = new HashMap<String,String>();
	static {
		List<String> citys=new TxtReader(ConfigCenter.getInstance().getProperty("cityspath")).getContentLineList();
		if(citys!= null && citys.size() !=0){
			for(String str:citys){
				CityMap.put(str, str);
			}
		}
	}

}
