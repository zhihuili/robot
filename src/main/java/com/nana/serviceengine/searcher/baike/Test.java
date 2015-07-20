package com.nana.serviceengine.searcher.baike;

import com.nana.serviceengine.searcher.zhidao.ZhidaoAnalysis;
import com.nana.serviceengine.searcher.zhidao.ZhidaoHtmlCreator;



public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		String res=ZhidaoHtmlCreator.getInstance().createAppHtml(ZhidaoAnalysis.getInstance().getBeanFromHtml("sadf事"));
		System.out.println(res);
//		System.out.println( ArrayList.class.getMethod("get", Integer.TYPE));
		
	}

	

}
