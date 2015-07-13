package com.nana.serviceengine.domain.coach.webapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
public class BusAPI {
	public static BusAPI coachAPI = new BusAPI();

	public static BusAPI getInstance() {
		if (coachAPI != null)
			return coachAPI;
		else
			coachAPI = new BusAPI();
		return coachAPI;
	}

	public BusAPI() {
	}
  public List<Bus> getCityCoachinfo(DomainParam coachParam){
	  ConfigCenter config=ConfigCenter.getInstance();
	  String apiKey=config.getProperty("");
	  String apiUrl=config.getProperty("");
	  Map<String,ParamItem> param=coachParam.getParams();
	  String start=param.get("start").getValue().toString(); 
	  String end=param.get("end").getValue().toString();
	  
	  BufferedReader reader = null;
	  List<Bus> carGroup=null;
	  StringBuffer sbf = new StringBuffer();
	  try {
		String path=new StringBuffer(apiUrl).append(apiKey).append("&from=").append(URLEncoder.encode(start, "UTF-8").replaceAll("\\+","%20")).append("&to=").append(URLEncoder.encode(end, "UTF-8")).toString();
        URL url;
		try {
			url = new URL(path);
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setReadTimeout(5000);
				connection.setConnectTimeout(3000);
				connection.connect();
				InputStream is = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
					sbf.append("\r\n");
				}
				reader.close();
				JSONObject oject = (JSONObject) JSONObject.parse(sbf.toString());
				carGroup=JSON.parseArray(oject.getJSONObject("result").getJSONArray("list").toString(),Bus.class);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	return carGroup;
  }
}
