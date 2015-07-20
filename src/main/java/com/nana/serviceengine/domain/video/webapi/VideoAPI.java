package com.nana.serviceengine.domain.video.webapi;

import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.common.util.EncodeChanger;
import com.nana.serviceengine.common.util.HttpServiceRequest;
import com.nana.serviceengine.dao.webapi.SimpleApiAccessor;
import com.nana.serviceengine.domain.video.domainparam.VideoParam;

public class VideoAPI implements SimpleApiAccessor{
	private static VideoAPI videoAPI = new VideoAPI();
	private VideoAPI(){}
	
	public static VideoAPI getInstance(){
		return videoAPI;
	}
	
	@Override
	public String loadData(Object param) {
		VideoParam vParam = (VideoParam)param;
		try{
			String apiPath = ConfigCenter.getInstance().getProperty("videoapi");
			String requestParam = "words=" + vParam.getParams().get("keyWord").getValue();
			String data = HttpServiceRequest.httpGet(apiPath+"?"+requestParam,"utf-8");	
			//这里进行了转码
			//data =  EncodeChanger.getInstance().changeCode(data, "utf-8", "utf-8");
			return data;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;	
		}
	}
	


}
