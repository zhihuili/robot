package com.nana.serviceengine.domain.food.webapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nana.common.message.GPS;
import com.nana.serviceengine.domain.food.bean.Foods;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class FoodAPI {

	private static final FoodAPI foodApi = new FoodAPI();

	private FoodAPI() {

	}

	public static FoodAPI getinstance() {
		return foodApi;
	}

	public List<Foods> queryFoodsByPosition(DomainParam foodParam) {

		String key = "97c65075a6eb98c3f42cb0e831a6fa25"; // 获取全国城市的key
		String url = "http://apis.juhe.cn/catering/query";
		// 获取参数
        double longitude;
        double latitude;
        Map<String,ParamItem> params = foodParam.getParams();
        GPS gps = (GPS) params.get("gps").getValue();
        longitude =  gps.getLongitude();
        latitude = gps.getLatitude();
		int radius = 1000;// 半径
		String urlAll = new StringBuffer(url).append("?key=").append(key)
				.append("&lng=").append(longitude).append("&lat=")
				.append(latitude).append(radius).toString();
		String charset = "UTF-8";
		String jsonResult = get(urlAll, charset);

		JSONObject object = JSON.parseObject(jsonResult);
		String code = object.getString("resultcode");
		Map<String, String> map = new HashMap<String, String>();
		List<Foods> foods = null;
		if (code.equals("200")) { // 访问成功
			System.out.println("resultcode:" + code + ",reason:"
					+ object.getString("reason"));
			JSONArray jsonArr = object.getJSONArray("result");
			 foods = JSON
					.parseArray(jsonArr.toString(), Foods.class);
		} else {// 访问失败
			System.out.println("resultcode" + code + ",reason:"
					+ object.getString("reason"));
		}
		return foods;
	}

	/**
	 * 
	 * @param urlAll
	 *            :请求接口
	 * @param charset
	 *            :字符编码
	 * @return 返回json结果
	 */
	public String get(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
