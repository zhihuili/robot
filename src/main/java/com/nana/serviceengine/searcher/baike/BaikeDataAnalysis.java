package com.nana.serviceengine.searcher.baike;

import com.nana.serviceengine.common.config.ConfigCenter;

public class BaikeDataAnalysis {
	private static BaikeDataAnalysis ba=new BaikeDataAnalysis();
	private String url;
	private BaikeDataAnalysis(){
		url = ConfigCenter.getInstance().getProperty("baikepath");
	}
	
	public static BaikeDataAnalysis getInstance(){
		return ba;
	}
	
	
}
