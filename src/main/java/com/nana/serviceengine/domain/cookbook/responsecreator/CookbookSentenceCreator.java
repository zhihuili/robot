package com.nana.serviceengine.domain.cookbook.responsecreator;

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
import com.nana.serviceengine.domain.cookbook.bean.Cookbook;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class CookbookSentenceCreator implements SentenceCreator {
	
	private static CookbookSentenceCreator wsc = new CookbookSentenceCreator();

	private CookbookSentenceCreator() {

	}

	public static CookbookSentenceCreator getInstance() {
		return wsc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<Cookbook> data = params.getResult();
		String res="";
		if(data == null && data.size() == 0){
			rma.setAudioText("不好意思，没有查询到相关数据");
			return rma;
		}
		if(data!=null){
			// 输出html
			 res = HtmlCenter.getInstance().getHtmlByList("cookbook.vm", data, "inputs","videohtml");
		}
		rma.setAudioText(res);
		return rma;
	}
}
