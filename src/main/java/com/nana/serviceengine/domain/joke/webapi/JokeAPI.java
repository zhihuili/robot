package com.nana.serviceengine.domain.joke.webapi;

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
import com.nana.serviceengine.domain.joke.bean.Joke;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
public class JokeAPI {
	public static JokeAPI coachAPI = new JokeAPI();

	public static JokeAPI getInstance() {
		if (coachAPI != null)
			return coachAPI;
		else
			coachAPI = new JokeAPI();
		return coachAPI;
	}

	public JokeAPI() {
	}
     public List<Joke> getJoke() throws UnsupportedEncodingException{
	  ConfigCenter config=ConfigCenter.getInstance();
	  String apiKey=config.getProperty("");
	  String apiUrl=config.getProperty("");
	  BufferedReader reader = null;
	  List<Joke> jokeGroup=null;
	  StringBuffer sbf = new StringBuffer();
	   String path=new String("http://japi.juhe.cn/joke/content/text.from?key=097ea8eb5ce816fe73efdede77872d87&page=1&pagesize=20");//此处参数稍后处理
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
			
			JSONObject oject = (JSONObject) JSONObject.parse(sbf.toString());
			
			 jokeGroup = JSON.parseArray(oject.getJSONObject("result")
					.getJSONArray("data").toString(), Joke.class);
			 reader.close();
				is.close();		 
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	return jokeGroup;
  }
}
