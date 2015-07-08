package com.nana.serviceengine.domain.weather.webapi;


import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.common.util.HttpServiceRequest;
import com.nana.serviceengine.dao.webapi.SimpleApiAccessor;
import com.nana.serviceengine.domain.weather.bean.WeatherFuture;
import com.nana.serviceengine.domain.weather.domainparam.WeatherParam;

/**
 * 这个类的主要工作就是通过调用API获取数据，并把数据放到参数Bean中
 * @author wds
 *
 */
public class WeatherAPI implements SimpleApiAccessor {
	private static WeatherAPI weatherApi = new WeatherAPI();

	private WeatherAPI() {
	}

	public static WeatherAPI getInstance() {
		return weatherApi;
	}

	@Override
	public String loadData(Object param) {
		WeatherParam weatherParam = (WeatherParam) param;
		String res = null;
		String param1 = null;
		try {
			String weatherkey = ConfigCenter.getInstance().getProperty(
					"juheappkey");
			String apiPath = null;
			String format = ConfigCenter.getInstance().getProperty("format");
			if (weatherParam.getLocation() == null) {
				if (weatherParam.getGpslat() != null
						&& weatherParam.getGpslon() != null) {
					param1 = "key=" + weatherkey + "&dtype=json&lon="
							+ weatherParam.getGpslon() + "&lat="
							+ weatherParam.getGpslat() + "&cityname="
							+ weatherParam.getLocation() + "&format=" + format;
					apiPath = ConfigCenter.getInstance().getProperty(
							"reportbygps");
				}

			} else {

				apiPath = ConfigCenter.getInstance().getProperty(
						"reportbyidorname");

				// 构建param
				param1 = "key=" + weatherkey + "&dtype=json&cityname="
						+ weatherParam.getLocation() + "&format=" + format;

				//
				// if (weatherParam.getDate() == null) {
				// // 默认为今天
				// weatherParam.setDate(new Date());
				// }

			}
			if (param1 != null && apiPath != null)
				res = HttpServiceRequest.httpGet(apiPath + "?" + param1);
			// System.out.println("请求结果：" + res);
			return res;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public void getWeatherData(WeatherParam weatherParam){
		String res = null;
		String param1 = null;
		try {
			String weatherkey = ConfigCenter.getInstance().getProperty(
					"juheappkey");
			String apiPath = null;
			String format = ConfigCenter.getInstance().getProperty("format");
			if (weatherParam.getLocation() == null) {
				if (weatherParam.getGpslat() != null
						&& weatherParam.getGpslon() != null) {
					param1 = "key=" + weatherkey + "&dtype=json&lon="
							+ weatherParam.getGpslon() + "&lat="
							+ weatherParam.getGpslat() + "&cityname="
							+ weatherParam.getLocation() + "&format=" + format;
					apiPath = ConfigCenter.getInstance().getProperty(
							"reportbygps");
				}

			} else {
				apiPath = ConfigCenter.getInstance().getProperty(
						"reportbyidorname");
				// 构建param
				param1 = "key=" + weatherkey + "&dtype=json&cityname="
						+ weatherParam.getLocation() + "&format=" + format;
			}
			if (param1 != null && apiPath != null)
				res = HttpServiceRequest.httpGet(apiPath + "?" + param1);
			JSONArray jsonArray = JSON.parseObject(res).getJSONObject("result").getJSONArray("future");
			List<WeatherFuture> listFuture = JSON.parseArray(jsonArray.toString(),WeatherFuture.class);
			weatherParam.setSenvenWeathers(listFuture);
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
	}

}
