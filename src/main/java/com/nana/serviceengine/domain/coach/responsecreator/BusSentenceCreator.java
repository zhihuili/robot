package com.nana.serviceengine.domain.coach.responsecreator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class BusSentenceCreator implements SentenceCreator {
	
	private static BusSentenceCreator wsc = new BusSentenceCreator();

	private BusSentenceCreator() {

	}

	public static BusSentenceCreator getInstance() {
		return wsc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<Bus> data = params.getResult();
		String res="";
		if(data == null && data.size() == 0){
			rma.setAudioText("不好意思，没有查询到相关数据");
			return rma;
		}
		if(data!=null){
			int index1=(Integer) paramItems.get("indexChange").getValue();
			System.out.println("你说的是"+index1);
			if(data.size()<5){
				
				 if(((index1 -1)* 5>data.size())){
					  Bus bus=new Bus();
					  bus.setArrive("没有数据了");
					  bus.setDate("没有数据了");
					  bus.setPrice("没有数据了");
					  bus.setStart("没有数据了");
					  List<Bus> data1 =new ArrayList<Bus>();  
					  data1.add(bus);
					 res = HtmlCenter.getInstance().getHtmlByList("bus.vm", data1, "inputs","videohtml");	 
					}
				 else{
					 res = HtmlCenter.getInstance().getHtmlByList("bus.vm", data, "inputs","videohtml");	
				 }
			}
			else if(data.size()>5){
				int index=(Integer) paramItems.get("indexChange").getValue();
				if((Integer) paramItems.get("indexChange").getValue()!=null&&((data.subList(index * 5 -5, data.size()).size())>=5)){
					res = HtmlCenter.getInstance().getHtmlByList("bus.vm", data.subList(index * 5 -5, index * 5), "inputs","videohtml");	
				}
				else if((data.subList(index * 5 -5, data.size()).size())<5){
					if(index*5>data.size()){
						System.out.println("无数据");
						 Bus bus=new Bus();
						  bus.setArrive("没有数据了");
						  bus.setDate("没有数据了");
						  bus.setPrice("没有数据了");
						  bus.setStart("没有数据了");
						  List<Bus> data1 =new ArrayList<Bus>();  
						  data1.add(bus);
						 res = HtmlCenter.getInstance().getHtmlByList("bus.vm", data1, "inputs","videohtml");	
						
					}else{
						res = HtmlCenter.getInstance().getHtmlByList("bus.vm", data.subList(index * 5 -5, data.size()), "inputs","videohtml");	
					}
				}
			}
		}
		rma.setAudioText(res);
		return rma;
	}
}
