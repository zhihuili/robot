package com.nana.serviceengine.dao.webapi.impl;

import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.common.util.EncodeChanger;
import com.nana.serviceengine.common.util.HttpServiceRequest;
import com.nana.serviceengine.dao.webapi.SimpleApiAccessor;
import com.nana.serviceengine.neuron.domainparam.impl.VideoParam;

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
			String requestParam = "words=" + vParam.getKeyWord();
			String data = HttpServiceRequest.httpGet(apiPath+"?"+requestParam);	
			//这里进行了转码
			data =  EncodeChanger.getInstance().changeCode(data, "iso-8859-1", "utf-8");
			return data;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;	
		}
	}
	


}
