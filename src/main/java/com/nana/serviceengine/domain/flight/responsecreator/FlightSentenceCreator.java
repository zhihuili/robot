package com.nana.serviceengine.domain.flight.responsecreator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.inout.bean.ResponseDisplay;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class FlightSentenceCreator implements SentenceCreator {

	private static final FlightSentenceCreator flightSentenceCreator = new FlightSentenceCreator();

	private FlightSentenceCreator() {
		// TODO Auto-generated constructor stub
	}

	public static FlightSentenceCreator getinstance() {
		return flightSentenceCreator;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String, ParamItem> paramItems = params.getParams();
		List<Flight> allflights = params.getResult();

		// 设置元素的下标
		for (int i = 0; i < allflights.size(); i++) {
			allflights.get(i).setIndex(i + 1);
		}

		String res = null;
		if (allflights == null || allflights.size() == 0) {
			rma.setAudioText("哎呀，没有你想要航班信息。");
			return rma;
		}
		// 获取第几页
		Integer index = (Integer) paramItems.get("indexChange").getValue();
		if (index == null)
			index = 1;
		// 分页的启示值,每页显示5个
		int start = (index - 1) * 5;
		int end = index * 5;
		// 获取结果
		String alert = params.getResult(allflights, start + 1);
		// 设置提示的话语
		rma.setAudioText(alert);
		if (index * 5 > allflights.size()){
			end = allflights.size() - 1;
		}
		
		List<Flight> flights = allflights.subList(start, end);
		// 重新设置下标为1-5
		for (int i = 0; i < flights.size(); i++) {
			flights.get(i).setIndex(i + 1);
		}

		
		// 获取用户的选择的那一航班信息
		Integer choice= (Integer)paramItems.get("choice").getValue();
		if (choice != null && choice != -1) {// 搜集到了用户的选择
			if (choice >= 1 && choice <= 5) {// 在选择范围
				int choose = start + choice - 1;
				if (choose < allflights.size()) {// 在搜索的航班中数据
					List<Flight> order = new ArrayList<Flight>();
					order.add(allflights.get(choose));
					ResponseDisplay responseDisplay = new ResponseDisplay();
					responseDisplay.setDataType("1");
					responseDisplay.setHeight("0.2");
					responseDisplay.setContent(HtmlCenter.getInstance()
							.getHtmlByList("flightfrm.vm", order, "flights",
									"demandhtml"));
					rma.setDisplayText(JSON.toJSONString(responseDisplay));
					return rma;
				} else {
					rma.setAudioText("对不起，没有找到您想要的下单的航班信息");
					return rma;
				}
			} else {
				rma.setAudioText("当前选择无效，请您选重新选择");
				return rma;
			}
		}

		if (flights != null) {
			ResponseDisplay responseDisplay = new ResponseDisplay();
			responseDisplay.setDataType("1");
			responseDisplay.setHeight("0.5");
			responseDisplay.setContent(HtmlCenter.getInstance().getHtmlByList(
					"flightfrm.vm", flights, "flights", "videohtml"));
			rma.setDisplayText(JSON.toJSONString(responseDisplay));
		}
		return rma;
	}
}
