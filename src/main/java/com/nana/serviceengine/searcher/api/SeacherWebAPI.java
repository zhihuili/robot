package com.nana.serviceengine.searcher.api;

import com.nana.serviceengine.common.util.EncodeChanger;
import com.nana.serviceengine.common.util.HttpServiceRequest;

public class SeacherWebAPI {
	private static SeacherWebAPI bda = new SeacherWebAPI();
	private SeacherWebAPI(){
	}
	
	private static SeacherWebAPI getInstance(){
		return bda;
	} 
	
	/**
	 * 跟据url和参数获取网页原始数据，参数格式如（word＝wwds）的字符串
	 * @param url
	 * @param param
	 * @return
	 */
	public String getData(String url,String param){
		StringBuilder builder = new StringBuilder();
		builder.append(url+"?"+param);
		String res = HttpServiceRequest.httpGet(builder.toString());
		return res;
	}
	
	/**
	 * 获取网页原始数据 同时指定转码
	 * @param url
	 * @param param
	 * @param oldCharset
	 * @param newCharset
	 * @return
	 */
	public String getData(String url,String param,String oldCharset,String newCharset){
		StringBuilder builder = new StringBuilder();
		builder.append(url+"?"+param);
		String res = HttpServiceRequest.httpGet(builder.toString());
		return EncodeChanger.getInstance().changeCode(res, oldCharset, newCharset);
	}
}
