package com.nana.serviceengine.inout.util;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.common.dic.HtmlDic;

public class HtmlDataFiller {
	private static HtmlDataFiller hdf = new HtmlDataFiller();
	private HtmlDataFiller(){}
	
	public static HtmlDataFiller getInstance(){
		return hdf;
	}
	
	/**
	 * 指定html的名称之后，用指定的数据源Bean来填充Html
	 * @param htmlName html的名称
	 * @param dataBean 数据源的bean
	 * @return 返回填充数据之后的html
	 */
	public String fillHtml(String htmlName,Object dataBean){
		String html  = HtmlDic.HTMLMAP.get(htmlName);
		if(html == null || "".equals(html)) return null;
		Class c = dataBean.getClass();
		Method[] methods = c.getMethods();
		return replaceTag(methods,html,dataBean);
	}
	
	/**
	 * 指定方法名来获取对象中的field值，只适用于获取bean的属性，且属性必须指定get方法
	 * @param methods
	 * @param target
	 * @param fieldName
	 * @return
	 */
	private Object getFieldValue(Method[] methods,Object target,String fieldName){
		StringBuilder builder = new StringBuilder();
		builder.append("get");
		builder.append(fieldName.substring(0, 1).toUpperCase());
		builder.append(fieldName.substring(1));
		for(int i=0;i<methods.length;i++){
			if(methods[i].getName().equals(builder.toString())){
				try {
					return methods[i].invoke(target);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}
	/**
	 * 替换
	 * @param methods
	 * @param html
	 * @param dataBean
	 * @return
	 */
	private String replaceTag(Method[] methods,String html,Object dataBean){
		Pattern pattern = Pattern.compile("\\{\\$[\\w]+\\}");
		StringBuffer res = new StringBuffer();
		Matcher matcher = pattern.matcher(html);
		while(matcher.find()){
			String fieldName = matcher.group().substring(2, matcher.group().length()-1);
			Object value = getFieldValue(methods,dataBean,fieldName);
			if(value != null)
				matcher.appendReplacement(res, value.toString());
			else
				matcher.appendReplacement(res, "");
		}
		matcher.appendTail(res);
		return res.toString();
	}
	
	
	 public static void main(String[] args) {
//	 String str =
//	 "<html><body><image url='{$name}'/><<image url='{$wds}'/> </body></html>";
//	 Pattern pattern = Pattern.compile("\\{\\$[\\w]+\\}");
//	 StringBuffer res = new StringBuffer();
//	 Matcher matcher = pattern.matcher(str);
//	 while(matcher.find()){
//	 String field = matcher.group();
//	 System.out.println(field.substring(2, field.length()-1));
//	 	matcher.appendReplacement(res, "wds");
//	 }
//	 matcher.appendTail(res);
//	 System.out.println(res);
		 Class c = GPS.class;
		 for(int i=0;i<c.getDeclaredFields().length;i++)
		 System.out.println(c.getDeclaredFields()[i].getName());
	 }
}
