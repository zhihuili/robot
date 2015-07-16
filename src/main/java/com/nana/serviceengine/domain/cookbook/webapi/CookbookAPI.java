package com.nana.serviceengine.domain.cookbook.webapi;

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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.domain.cookbook.bean.Cookbook;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class CookbookAPI {
	public static CookbookAPI coachAPI = new CookbookAPI();

	public static CookbookAPI getInstance() {
		if (coachAPI != null)
			return coachAPI;
		else
			coachAPI = new CookbookAPI();
		return coachAPI;
	}

	public CookbookAPI() {
	}

	public List<Cookbook> getCityCoachinfo(DomainParam coachParam)
			throws UnsupportedEncodingException {
		ConfigCenter config = ConfigCenter.getInstance();
		String apiKey = config.getProperty("");
		String apiUrl = config.getProperty("");
		Map<String, ParamItem> param = coachParam.getParams();
		String cookbook = (String) param.get("cookbook").getValue();
		BufferedReader reader = null;
		List<Cookbook> carGroup = null;
		StringBuffer sbf = new StringBuffer();
		String path = new StringBuffer(
				"http://apis.juhe.cn/cook/query?key=2d5bf845b48881bca34d44781bfb6752&menu="
						+ cookbook + "&rn=10&pn=3").toString();
		System.out.println(path);
		URL url;
		try {
			url = new URL(path);
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setReadTimeout(5000);
				connection.setConnectTimeout(5000);
				connection.connect();
				InputStream is = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
					sbf.append("\r\n");
				}
				reader.close();
				JSONObject oject = (JSONObject) JSONObject
						.parse(sbf.toString());
				carGroup = JSON.parseArray(oject.getJSONObject("result")
						.getJSONArray("date").toString(), Cookbook.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return carGroup;
	}
}
