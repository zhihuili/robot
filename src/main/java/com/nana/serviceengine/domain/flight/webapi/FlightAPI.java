package com.nana.serviceengine.domain.flight.webapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class FlightAPI {

 private static FlightAPI flightApi = new FlightAPI();
 
  private FlightAPI(){
 	 
  }
  
  public static FlightAPI getinstance(){
	  return flightApi;
  }
  
	/**
	 * @param start 始发地
	 *            
	 * @param end 终点站
	 *            
	 * @return
	 */
	public  Map<String, String> getCitycode() {

		String key = "4e2d9dc19b6ea56b66e3900cbeeebf2c"; // 获取全国城市的key
		String url = "http://apis.juhe.cn/plan/city?key=";
		
		String urlAll = new StringBuffer(url).append(key).toString();
		String charset = "UTF-8";
		String jsonResult = get(urlAll, charset);

		JSONObject object = JSON.parseObject(jsonResult);
		String code = object.getString("error_code");
		Map<String, String> map = new HashMap<String, String>();

		if (code.equals("0")) { // 访问成功
			System.out.println("error_code:" + code + ",reason:"
					+ object.getString("reason"));
			JSONArray jsonArr = object.getJSONArray("result");

			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject o = jsonArr.getJSONObject(i);
				map.put(o.getString("city"), o.getString("spell"));
			}
		} else {// 访问失败
			System.out.println("error_code:" + code + ",reason:"
					+ object.getString("reason"));
		}
		return map;
	}

	/**
	 * 
	 * @param flightParam 请求参数
	 * @return
	 */
	public  List<Flight> searchFligthResult(DomainParam flightParam) {
        //获取appkey以及访问的api路径
		//ConfigCenter config =  ConfigCenter.getInstance();
		//String key = config.getProperty("flightkey");
		//String url = config.getProperty("flightapi");
		String key = "4e2d9dc19b6ea56b66e3900cbeeebf2c";
		String url = "http://apis.juhe.cn/plan/bc";
		
		String start =null;//起始站
		String end = null;//终点站
		 
		Map<String, ParamItem> params = flightParam.getParams();
		start = params.get("start").getValue().toString();
		end = params.get("end").getValue().toString();
		String date = params.get("date").toString();
		
		String urlAll = new StringBuffer(url).append("?key=").append(key).append("&start=")
				.append(start).append("&end=").append(end).append("&date=")
				.append(date).toString();

		String charset = "UTF-8";
		String jsonResult = get(urlAll, charset);

		JSONObject object = JSON.parseObject(jsonResult);
		// System.out.println(object);
		List<Flight> listinfo = new ArrayList<Flight>();
		String code = object.getString("error_code");

		if (code.equals("0")) { // 访问成功
			System.out.println("error_code:" + code + ",reason:"
					+ object.getString("reason"));
			listinfo = JSON.parseArray(
					object.getJSONArray("result").toString(), Flight.class);

		} else {// 访问失败
			System.out.println("error_code:" + code + ",reason:"
					+ object.getString("reason"));
		}
		return listinfo;
	}

	/**
	 * 
	 * @param urlAll
	 *            :请求接口
	 * @param charset
	 *            :字符编码
	 * @return 返回json结果
	 */
	public  String get(String urlAll, String charset) {
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
