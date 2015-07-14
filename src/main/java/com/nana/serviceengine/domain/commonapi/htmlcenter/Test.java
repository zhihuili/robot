package com.nana.serviceengine.domain.commonapi.htmlcenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBean bean = new TestBean();
		String res = HtmlCenter.getInstance().getHtmlByBean("frm.vm", bean);
		System.out.println(res);
		
//		List<Map<String,String>> inputs=new ArrayList<Map<String,String>>();
//		HashMap<String, String> input1 = new HashMap<String, String>();
//        input1.put("id", "title");
//        input1.put("title", "标题：");
//        inputs.add(input1);
//        HashMap<String, String> input2 = new HashMap<String, String>();
//        input2.put("id", "brief");
//        input2.put("title", "摘要：");
//        inputs.add(input2);
//		String res = HtmlCenter.getInstance().getHtmlByList("frm.vm", inputs, "inputs");
//		System.out.println(res);
	}

}
