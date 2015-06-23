package com.nana.serviceengine.webapi.impl;

import com.nana.serviceengine.bean.VideoParam;
import com.nana.serviceengine.util.ConfigCenter;
import com.nana.serviceengine.util.HttpServiceRequest;
import com.nana.serviceengine.webapi.APIAccessor;

public class VideoAPI implements APIAccessor{
	private static VideoAPI videoAPI = new VideoAPI();
	private VideoAPI(){}
	
	public static VideoAPI getInstance(){
		return videoAPI;
	}
	
	@Override
	public String getInfo(Object param) {
		VideoParam vParam = (VideoParam)param;
		try{
		
			String apiPath = ConfigCenter.getInstance().getProperty("videoapi");
			String requestParam = "words=" + vParam.getKeyWord();
			return HttpServiceRequest.httpGet(apiPath+"?"+requestParam);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;	
		}
	}

}
