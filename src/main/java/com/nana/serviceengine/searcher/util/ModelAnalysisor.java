package com.nana.serviceengine.searcher.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.util.TxtReader;
import com.nana.serviceengine.searcher.bean.BeanItem;
import com.nana.serviceengine.searcher.bean.MethodItem;
import com.nana.serviceengine.searcher.bean.Param;

public class ModelAnalysisor {
	private static ModelAnalysisor ma = new ModelAnalysisor();

	private ModelAnalysisor() {
	}

	public static ModelAnalysisor getInstance() {
		return ma;
	}
	/**
	 * 解析Model配置文件
	 * @param modelPath Model文件的地址
	 * @param methodItems
	 * @param reducer
	 * @param beanItems
	 */
	public void analysis(String modelPath,Map<String,MethodItem> methodItems,List<String> reducer,List<BeanItem> beanItems){
		List<String> lines = new TxtReader(modelPath).getContentLineList();
		int i=0;
		for(String line:lines){
			if("tag_reduce".equals(line.trim())){
				i=1;
				continue;
			}
			else if("property_collector".equals(line.trim())){
				i=2;
				continue;
			}
			if(i == 0){
				analysisMethodItem(line,methodItems);	
			}else if(i == 1){
				reducer.add(line.trim());
			}else if(i == 2){
				analysisBeanItem(line,beanItems);
			}
		}
	}
	/**
	 * 解析property_collector部分
	 * @param line
	 * @param beanItems
	 */
	private void analysisBeanItem(String line,List<BeanItem> beanItems){
		try{
			String[] tmp = line.split("\\.\\.");
			BeanItem beanItem = new BeanItem();
			beanItem.setSetMethodName(tmp[0]);
			beanItem.setMethodIndex(tmp[1]);
			beanItems.add(beanItem);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 解析tag_reduce部分
	 * @param line
	 * @param methodItems
	 */
	private void analysisMethodItem(String line,Map<String,MethodItem> methodItems){
		try{
			String[] tmp = line.split("\\.\\.");
			MethodItem mi = new MethodItem();
			mi.setIndex(tmp[0]);
			mi.setMethodName(tmp[1]);
			if(tmp.length>=3){		
				String[] tmp1 = tmp[2].split(",");
				List<Param> params = new ArrayList<Param>();
				for(int i=0;i<tmp1.length;i++){
					String[] tmp2 = tmp1[i].split("\\|");
					Param param = new Param();
					param.setType(tmp2[0]);
					param.setValue(tmp2[1]);
					params.add(param);
				}
				mi.setParams(params);
			}
			methodItems.put(tmp[0], mi);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
