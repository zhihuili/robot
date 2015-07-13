package com.nana.serviceengine.domain.commonapi.htmlcenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBean {
	private String name;
	private List<Map<String,String>> inputs=new ArrayList<Map<String,String>>();;
	
	public TestBean(){
		name="wds";
		
        HashMap<String, String> input1 = new HashMap<String, String>();
        input1.put("id", "title");
        input1.put("title", "标题：");
        inputs.add(input1);
        HashMap<String, String> input2 = new HashMap<String, String>();
        input2.put("id", "brief");
        input2.put("title", "摘要：");
        inputs.add(input2);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Map<String, String>> getInputs() {
		return inputs;
	}
	public void setInputs(List<Map<String, String>> inputs) {
		this.inputs = inputs;
	}
	
	
}
