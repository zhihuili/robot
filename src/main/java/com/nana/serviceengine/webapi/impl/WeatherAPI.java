package com.nana.serviceengine.webapi.impl;


import com.nana.serviceengine.bean.WeatherParam;
import com.nana.serviceengine.util.ConfigCenter;
import com.nana.serviceengine.util.HttpServiceRequest;
import com.nana.serviceengine.webapi.APIAccessor;

public class WeatherAPI implements APIAccessor {
	private static WeatherAPI weatherApi = new WeatherAPI();

	private WeatherAPI() {
	}

	public static WeatherAPI getInstance() {
		return weatherApi;
	}

	@Override
	public String getInfo(Object param) {
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

}
