package com.nana.serviceengine.searcher.zhidao;

import java.util.List;

import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.searcher.bean.ComminDic;


public class ZhidaoHtmlCreator {
	private static ZhidaoHtmlCreator zhc = new ZhidaoHtmlCreator();

	private ZhidaoHtmlCreator() {
	}

	public static ZhidaoHtmlCreator getInstance() {
		return zhc;
	}
	
	public String createAppHtml(List<Object> data){
		if(data.size() == 0) return ComminDic.ERRORPAGE;
		return HtmlCenter.getInstance().formatVM("zhidaohtml.vm", data.get(0));
	}
	
}
