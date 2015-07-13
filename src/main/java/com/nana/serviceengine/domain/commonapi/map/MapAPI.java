package com.nana.serviceengine.domain.commonapi.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.common.util.HttpServiceRequest;
import com.nana.serviceengine.domain.commonapi.map.bean.Address;

public class MapAPI {
	private static MapAPI mapAPI = new MapAPI();
	private MapAPI(){}
	
	public static MapAPI getInstance(){
		return mapAPI;
	}
	/**
	 * 根据GPS信息获取地址信息
	 * @param gps
	 * @return 地址
	 */
	public Address getDetailInfoByGps(GPS gps){
		if(gps == null || gps.getLat() == null || gps.getLon() == null || "".equals(gps.getLat())||"".equals(gps.getLon()))
		return null;		
		
		String url = ConfigCenter.getInstance().getProperty("baidugetlocbygps");
		String ak = ConfigCenter.getInstance().getProperty("baidumapak");
		String location = "&location="+gps.getLat()+","+gps.getLon();
		StringBuilder builder = new StringBuilder();
		//添加参数信息
		builder.append(url+"?output=json&pois=1&coordtype=wgs84ll&ak="+ak+location);
		String json = HttpServiceRequest.httpGet(builder.toString());	
		return parseJSON(json);
	}
	
	private Address parseJSON(String json){		
		JSONObject jsonObject = JSON.parseObject(json).getJSONObject("result");
		Address address = jsonObject.getObject("addressComponent", Address.class);
		address.setFormatted_address(jsonObject.getString("formatted_address"));
		return address;
	}
	
	
}
