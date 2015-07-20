package com.nana.serviceengine.domain.weather.webapi;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.common.util.HttpServiceRequest;
import com.nana.serviceengine.domain.weather.bean.WeatherFuture;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

/**
 * 这个类的主要工作就是通过调用API获取数据，并把数据放到参数Bean中
 * 
 * @author wds
 *
 */
public class WeatherAPI{
	private static WeatherAPI weatherApi = new WeatherAPI();

	private WeatherAPI() {
	}

	public static WeatherAPI getInstance() {
		return weatherApi;
	}

	public List<WeatherFuture> getWeatherData(DomainParam weatherParam) {
		String res = null;
		String param1 = null;
		try {
			String weatherkey = ConfigCenter.getInstance().getProperty(
					"juheappkey");
			String apiPath = null;
			String format = ConfigCenter.getInstance().getProperty("format");
			Map<String, ParamItem> params = weatherParam.getParams();
			if (params.get("location").getValue() == null) {
				if (params.get("gps") != null) {
					GPS gps = (GPS) params.get("gps").getValue();
					if (gps.getLat() != null && gps.getLon() != null) {
						param1 = "key=" + weatherkey + "&dtype=json&lon="
								+ gps.getLon() + "&lat=" + gps.getLat()
								+ "&format=" + format;
						apiPath = ConfigCenter.getInstance().getProperty(
								"reportbygps");
					}
				}

			} else {
				apiPath = ConfigCenter.getInstance().getProperty(
						"reportbyidorname");
				// 构建param
				param1 = "key=" + weatherkey + "&dtype=json&cityname="
						+ ((String[])params.get("location").getValue())[0] + "&format=" + format;
			}
			if (param1 != null && apiPath != null)
				res = HttpServiceRequest.httpGet(apiPath + "?" + param1);
			JSONArray jsonArray = JSON.parseObject(res).getJSONObject("result").getJSONArray("future");
			List<WeatherFuture> listFuture = JSON.parseArray(
					jsonArray.toString(), WeatherFuture.class);
			return listFuture;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	
}
