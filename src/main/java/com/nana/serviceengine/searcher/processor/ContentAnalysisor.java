package com.nana.serviceengine.searcher.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nana.serviceengine.common.util.MethodInvoker;
import com.nana.serviceengine.searcher.bean.ItemRule;
import com.nana.serviceengine.searcher.util.HtmlUtil;

public class ContentAnalysisor {
	private static ContentAnalysisor ca = new ContentAnalysisor();

	private ContentAnalysisor() {
	}

	public static ContentAnalysisor getInstance() {
		return ca;
	}
	
	/**
	 * 从html标签中获取已填充数据的bean
	 * @param tagItems
	 * @param itemRules
	 * @param beanClass
	 * @return
	 */
	public List<Object> getBeanFromItemHtml(List<String> tagItems,
			List<ItemRule> itemRules, Class<?> beanClass) {
		try {
			List<Object> res = new ArrayList<Object>();
			//遍历每个标签
			for (int i = 0; i < tagItems.size(); i++) {
				
				int start = 0;
				int end = 0;
				String item = tagItems.get(i);
				//实例一个bean用来接收数据
				Object tmpBean = beanClass.newInstance();
				//开始从标签中抽取数据
				for(ItemRule itemRule:itemRules){
					Pattern headPattern = Pattern.compile(itemRule.getFirstPattern());
					Pattern endPattern = Pattern.compile(itemRule.getEndPattern());
					Matcher headMatcher = headPattern.matcher(item);
					if(headMatcher.find()){
						start=headMatcher.end();
					}
					
					Matcher endMatcher = headPattern.matcher(tagItems.get(i).substring(start));
					if(endMatcher.find()){
						end = endMatcher.start();
					}
					String substr=item.substring(start, end);
					//去除html标签
					String res1 = HtmlUtil.getInstance().deleteHtmlTags(substr);
					//去除停用词
					String res2 = HtmlUtil.getInstance().deleteStopWords(res1, itemRule.getStopWords());
					//将结果设置到bean中
					MethodInvoker.invokeOneParamMethod(tmpBean, itemRule.getValueName(), res2);
				}
				res.add(tmpBean);
			}
			return res;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
