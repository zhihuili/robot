package com.nana.serviceengine.domain.location.webapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.domain.location.bean.Position;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class LocationAPI {
	private static LocationAPI locationAPI = new LocationAPI();

	private LocationAPI() {

	}

	public static LocationAPI getInstance() {
		return locationAPI;
	}

	public List<Position> getPositionData(DomainParam locationParam) {
		ConfigCenter config = ConfigCenter.getInstance();
		Map<String, ParamItem> params = locationParam.getParams();
		List<Position> listPosition =new ArrayList<Position>();
		ParamItem gpsItem = params.get("gps");
		GPS gps = (GPS) gpsItem.getValue();
		String longitude = gps.getLon();
		String latitude = gps.getLat();
		String url = "http://apis.juhe.cn/geo/?key=fddcd99169be39c686bffe54d839e881&lat="+ latitude + "&lng=" + longitude + "&type=1";
		String json = loadJson(url);
		JSONObject jsonObject = JSON.parseObject(json).getJSONObject("result");
		Position position = JSON.parseObject(jsonObject.toString(), Position.class);
		listPosition.add(position);
		return listPosition;
	}

	public String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
