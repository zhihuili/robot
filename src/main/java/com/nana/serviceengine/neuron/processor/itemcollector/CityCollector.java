package com.nana.serviceengine.neuron.processor.itemcollector;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.common.util.TxtReader;

public class CityCollector {
	private static CityCollector cc = new CityCollector();
	private CityCollector(){}
	
	public static CityCollector getInstance(){
		return cc;
	}
	/**
	 * 过期 找到句子中的城市名
	 * @param inputs
	 * @return
	 */
	public String[] parseCity(String inputs) {
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
	public String[] parseCity(List<Term> terms) {
		List<String> res = new ArrayList<String>();
		for(int i=0;i<terms.size();i++){
			DomainKeyWord dkw  = DomainDic.domainKeyWord.get(terms.get(i).getRealName());
			if(dkw == null) continue;
			if("address".equals(dkw.getDomain())){
				res.add(dkw.getValue());
			}
		}
		return res.toArray(new String[]{});
	}
}
