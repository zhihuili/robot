package com.nana.serviceengine.util;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CityCollector {
	/**
	 * 找到句子中的城市名
	 * @param inputs
	 * @return
	 */
	public static String[] parseCity(String inputs) {
		String city = new TxtReader("resources/chinacitys").getContent();
		List<String> res = new ArrayList<String>();
		try {
			JSONObject data =new JSONObject(city);
			JSONArray provinces =  data.getJSONArray("data");
			for(int i =0 ;i< provinces.length();i++){
				JSONObject tmp = provinces.getJSONObject(i);
				String provinceName =tmp.get("name").toString();  
				if(inputs.contains(provinceName)){
					res.add(provinceName);
				}else{
					JSONArray cityArray = tmp.getJSONArray("cities");
					for(int j = 0;j< cityArray.length();j++){
						String cityName = cityArray.getString(j).toString();
						if(inputs.contains(cityName)){
							res.add(cityName);
						}
					}
				}
			}
			
			return res.toArray(new String[]{});
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据分词后的结果找到句子中的城市名
	 * @param inputs 
	 * @return
	 */
	public static String[] parseCity(List<Term> terms) {
		List<String> res = new ArrayList<String>();
		for(int i=0;i<terms.size();i++){
			if(terms.get(i).getNatureStr().equals("ns")){
				res.add(terms.get(i).getRealName());
			}
		}
		return (String[])res.toArray();
	}
}
