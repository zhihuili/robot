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
			// 输出html
			 res = HtmlCenter.getInstance().getHtmlByList("bus.vm", data, "inputs");
		}
		rma.setAudioText(res);
		return rma;
	}
}
