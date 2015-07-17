package com.nana.serviceengine.domain.train.webapi;

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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.domain.train.bean.Train;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class TrainAPI {
	private static TrainAPI trainAPI = new TrainAPI();

	private TrainAPI() {

	}

	public static TrainAPI getInstance() {
		return trainAPI;
	}

	public List<Train> getTrainData(DomainParam trainParam) {
		String from = null;
		String to = null;
		String date = null;
		String type = null;
		Map<String, ParamItem> params = trainParam.getParams();
		List<Train> restTickets = new ArrayList<Train>();
		from = params.get("start").getValue().toString();
		to = params.get("end").getValue().toString();
		date = params.get("date").getValue().toString();
		type = params.get("type").getValue().toString();
       System.out.println(date);
		if(type==null){
			restTickets=queryTicketsByDate(date,from,to);
		}else{
			restTickets=queryByTraintype(date, from, to, type);
		}
		return restTickets;
	}

	public List<Train> queryTicketsByDate(String date, String from, String to) {
		String url = "http://apis.juhe.cn/train/yp?key=22e5a1e4fd3bd6ad78ee5a7c001164b5&dtype=json&from="
				+ from + "&to=" + to + "&date=" + date + "&tt=";
		List<Train> restTickets = new ArrayList<Train>();
		String json = loadJson(url);
		JSONArray jsonArray = JSON.parseObject(json).getJSONArray("result");
		JSONObject object = JSON.parseObject(json);
		String code = object.getString("error_code");
		if (code.equals("0")) {
			restTickets = JSON.parseArray(jsonArray.toString(), Train.class);
		} else {
			System.out.println("error_code:" + code + ",reason:"
					+ object.getString("reason"));
		}
		return restTickets;
	}

	public List<Train> queryByTraintype(String date, String from, String to,
			String type) {
		String url = "http://apis.juhe.cn/train/yp?key=22e5a1e4fd3bd6ad78ee5a7c001164b5&dtype=json&from="
				+ from + "&to=" + to + "&date=" + date + "&tt=" + type;
		List<Train> restTickets = new ArrayList<Train>();
		String json = loadJson(url);
		JSONArray jsonArray = JSON.parseObject(json).getJSONArray("result");
		JSONObject object = JSON.parseObject(json);
		String code = object.getString("error_code");
		if (code.equals("0")) {
			restTickets = JSON.parseArray(jsonArray.toString(), Train.class);
		} else {
			System.out.println("error_code:" + code + ",reason:"
					+ object.getString("reason"));
		}
		return restTickets;
	}

	public String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
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
