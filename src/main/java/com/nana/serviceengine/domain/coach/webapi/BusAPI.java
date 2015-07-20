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
import com.nana.serviceengine.domain.coach.bean.BusErrorCode;
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
  public List<Bus> getCityCoachinfo(DomainParam coachParam) throws UnsupportedEncodingException{
	  ConfigCenter config=ConfigCenter.getInstance();
	  Map<String,ParamItem> param=coachParam.getParams();
	  String start=(String) param.get("start").getValue();
	  String end=(String) param.get("end").getValue();
//	  ParamItem start=param.get("start"); 
//	  System.out.println("START"+start.getCollectResult());
//	  ParamItem end=param.get("end"); 
//	  System.out.println("END:"+end.getCollectResult());
	  BufferedReader reader = null;
	  List<Bus> carGroup=null;
	  StringBuffer sbf = new StringBuffer();
	  String path=new StringBuffer("http://op.juhe.cn/onebox/bus/query_ab?key=").append("cc12dbba24917a2f97d4e3aecbb0c5d7").append("&from=").append(start).append("&to=").append(end).toString();
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
			BusErrorCode errorCode = JSON.parseObject(sbf.toString(), BusErrorCode.class);
			if(!errorCode.getError_code().equals("0")){
				carGroup=null;
				return carGroup;
			}
			    carGroup=JSON.parseArray(oject.getJSONObject("result").getJSONArray("list").toString(),Bus.class);
			    return carGroup;
		} catch (IOException e) {
			e.printStackTrace();
		}
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	return carGroup;
  }
}
