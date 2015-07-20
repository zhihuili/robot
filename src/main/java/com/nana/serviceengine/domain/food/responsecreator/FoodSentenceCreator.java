package com.nana.serviceengine.domain.food.responsecreator;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.domain.food.bean.Foods;
import com.nana.serviceengine.inout.bean.ResponseDisplay;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class FoodSentenceCreator implements SentenceCreator {

	private static final FoodSentenceCreator fc = new FoodSentenceCreator();

	private FoodSentenceCreator() {
	}

	public static FoodSentenceCreator getinstance() {
		return fc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String, ParamItem> paramItems = params.getParams();
		List<Foods> foods = params.getResult();

		// 没有查询到数据
		if (foods == null && foods.size() == 0) {
			rma.setAudioText("哎呀，没有找到你想要的美食信息。");
		}

		String res = null;
		if (foods != null) {
			ResponseDisplay rd = new ResponseDisplay();
			rd.setDataType("1");
			rd.setHeight("0.8");
			rd.setContent(HtmlCenter.getInstance().getHtmlByList("food.vm", foods,
					"foods","videohtml"));
			rma.setDisplayText(JSON.toJSONString(rd));
		}
		return rma;
	}

}
