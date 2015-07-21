package com.nana.serviceengine.searcher.zhidao;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.searcher.bean.Model;
import com.nana.serviceengine.searcher.processor.ContentAnalysisor;
import com.nana.serviceengine.searcher.util.ModelAnalysisor;

public class ZhidaoAnalysis {
	private static ZhidaoAnalysis za=new ZhidaoAnalysis();
	
	private String url;
	private Model model;
	
	private ZhidaoAnalysis(){
		url = ConfigCenter.getInstance().getProperty("zhidaopath");
		model = new Model();
		ModelAnalysisor.getInstance().analysis("resources/htmlpattern/zhidao", model.getMethodItems(), model.getReducer(), model.getBeanItems());
	}
	
	public static ZhidaoAnalysis getInstance(){
		return za;
	}
	
	public List<Object> getBeanFromHtml(String keyWord){
		try{
			Document doc = Jsoup.connect(url+"?word="+keyWord).get();
			return ContentAnalysisor.getInstance().getBeanFromItemHtml(doc, model, ZhidaoBean.class);		
		}catch(Exception ex){
			;
		}
		return null;
	} 
	
	
	
	
	
	
	
	
	
}
