package com.nana.serviceengine.domain.weather.responsecreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.weather.bean.WeatherFuture;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class WeatherSentenceCreator implements SentenceCreator {

	private static WeatherSentenceCreator wsc = new WeatherSentenceCreator();

	private WeatherSentenceCreator() {

	}

	public static WeatherSentenceCreator getInstance() {
		return wsc;
	}


	public String createSentence(Map<String, Object> params) {
		String data = (String) params.get("data");
		Date date = (Date) params.get("date");
	
		String res = null;
		String cityname = null;
		String weather = null;
		String temprature = null;
		String wind = null;

		try {
			JSONObject jsonRes = new JSONObject(data);
			String resultCode = jsonRes.getString("resultcode");

			String jsonTimeKey = "today";
			if (date != null && date.getDate() != new Date().getDate()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				jsonTimeKey = "day_" + sdf.format(date);
			}

			if ("200".equals(resultCode)) {
				JSONObject result = jsonRes.getJSONObject("result");
				JSONObject today = result.getJSONObject("today");

				JSONObject aimJson = null;
				cityname = today.getString("city");

				if (jsonTimeKey.equals("today")) {
					aimJson = today;
				} else {
					aimJson = result.getJSONObject("future").getJSONObject(
							jsonTimeKey);
				}
				if (aimJson == null) {
					res = "我只能查询最近6天的天气。";
				} else {
					weather = aimJson.getString("weather");
					wind = aimJson.getString("wind");
					temprature = aimJson.getString("temperature");
					res = (date.getMonth()+1)+"月" + date.getDate() +"日, " + cityname + ", 天气：" + weather + ", 风力："+wind
							+ ", 温度："+ temprature;
				}

			} else {
				res = "抱歉-_-# 我没有查到相关的天气信息，我会努力改进的";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		//构造返回数据
		ResponseMessageAdapter rma =new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<WeatherFuture> data =params.getResult();
		if(data == null && data.size() == 0){
			rma.setAudioText("哎呀，没有数据。");
			return rma;
		}
		Date aimDate = ((Date[]) paramItems.get("date").getValue())[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String aimDateStr = sdf.format(aimDate);
		WeatherFuture res = null;
		for(WeatherFuture wf:data){
			if(aimDateStr.equals(wf.getDate())){
				res = wf;
				break;
			}
		}
		if(res == null){
			rma.setAudioText("我只能查到最近6天的天气！");
			return rma;
		}
		String tmp = ((String[])paramItems.get("location").getValue())[0] + (aimDate.getMonth()+1)+"月" + aimDate.getDate() +"日的天气情况如下：" + res.getWeather() + ", 风力："+res.getWind()
				+ ", 温度："+ res.getTemperature();
		rma.setAudioText(tmp);
		return rma;
	}

}
