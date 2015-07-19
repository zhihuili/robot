package com.nana.serviceengine.domain.coach.responsecreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.inout.bean.ResponseDisplay;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class BusSentenceCreator implements SentenceCreator {

	private static BusSentenceCreator wsc = new BusSentenceCreator();
	Map<String, List<Bus>> map = new HashMap<String, List<Bus>>();

	private BusSentenceCreator() {

	}

	public static BusSentenceCreator getInstance() {
		return wsc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String, ParamItem> paramItems = params.getParams();
		ResponseDisplay responseDisplay = new ResponseDisplay();
		Integer index = (Integer) paramItems.get("indexChange").getValue();
		Integer choice = (Integer) paramItems.get("choice").getValue();
		if(index==null){
			index=1;	
		}
		List<Bus> bus = params.getResult();
		System.out.println("choice"+choice);
		if(bus==null||bus.size()==0){
			rma.setAudioText("对不起，没有数据。");
			return rma;
		}
		if (bus.size() <= 5) {
			if (index!=null&&index != 1) {
				rma.setAudioText("对不起，已经没有数据。");
				return rma;
			}
			if (choice!=null&&choice != -1 && choice <=bus.size()) {
				List <Bus> busDingdan=new ArrayList<Bus>();
				busDingdan.add(bus.get(choice-1));
				String choiceContent1 = HtmlCenter.getInstance().getHtmlByList(
						"bus.vm", busDingdan, "inputs", "demandhtml");
				responseDisplay.setDataType("1");
				responseDisplay.setHeight("0.8");
				responseDisplay.setContent(choiceContent1);
				rma.setDisplayText(JSON.toJSONString(responseDisplay));
				return rma;
				
				
				
			} else if (choice!=null&&choice != -1 && choice > bus.size()) {
				rma.setAudioText("对不起，此列表没有您选择的班次信息，请重新选择");
				return rma;
			}

			String choiceContent = HtmlCenter.getInstance().getHtmlByList(
					"bus.vm", bus, "inputs", "videohtml");
			responseDisplay.setDataType("1");
			responseDisplay.setHeight("0.8");
			responseDisplay.setContent(choiceContent);
			rma.setDisplayText(JSON.toJSONString(responseDisplay));
			return rma;
		}

		else if (bus.size() > 5) {

			if (index == 1 && (index * 5) <= bus.size()) {
				if (choice!=null&&choice != -1 && choice > 5) {
					rma.setAudioText("对不起，此列表没有您选择的班次信息，请重新选择");
					return rma;
				} else if (choice!=null&&choice != -1 && choice < 5) {
					List <Bus> busDingdan=new ArrayList<Bus>();
					busDingdan.add(bus.get(choice));
					String choiceContent = HtmlCenter.getInstance()
							.getHtmlByList("bus.vm",busDingdan, "inputs",
									"demandhtml");
					responseDisplay.setDataType("1");
					responseDisplay.setHeight("0.8");
					responseDisplay.setContent(choiceContent);
					rma.setDisplayText(JSON.toJSONString(responseDisplay));
					return rma;
				}
				String choiceContent = HtmlCenter.getInstance().getHtmlByList(
						"bus.vm", bus.subList(0, 5), "inputs", "videohtml");
				responseDisplay.setDataType("1");
				responseDisplay.setHeight("0.8");
				responseDisplay.setContent(choiceContent);
				rma.setDisplayText(JSON.toJSONString(responseDisplay));
				return rma;

			} else if (index != 1 && (index * 5) < bus.size()) { // 显示中间结果

				if (choice!=null&&choice != -1 && choice > 5) {
					rma.setAudioText("对不起，此列表没有您选择的班次信息，请重新选择");
					return rma;
				}
				if (choice!=null&&choice != -1 && choice < 5) {
					List <Bus> busDingdan=new ArrayList<Bus>();
					busDingdan.add(bus.get((index - 1) * 5 + choice - 1));
					String choiceContent1 = HtmlCenter.getInstance()
							.getHtmlByList("bus.vm", busDingdan, "inputs",
									"demandhtml");
					responseDisplay.setDataType("1");
					responseDisplay.setHeight("0.8");
					responseDisplay.setContent(choiceContent1);
					rma.setDisplayText(JSON.toJSONString(responseDisplay));
					return rma;
				}
				String choiceContent = HtmlCenter.getInstance().getHtmlByList(
						"bus.vm", bus.subList((index - 1) * 5, index * 5),
						"inputs", "videohtml");
				responseDisplay.setDataType("1");
				responseDisplay.setHeight("0.8");
				responseDisplay.setContent(choiceContent);
				rma.setDisplayText(JSON.toJSONString(responseDisplay));
				return rma;
			} 
			else if (index != 1 && (index * 5) > bus.size()) {// 显示末尾页结果
				if (index > (bus.size() / 5 + 1)) {
					rma.setAudioText("对不起，已经没有数据");
					return rma;
				}
				if (choice!=null&&choice != -1 && choice > bus.size() % 5) {
					rma.setAudioText("对不起，此列表没有您选择的班次信息，请重新选择");
					return rma;
				} else if (choice!=null&&choice != -1 && choice <= bus.size() % 5) {
					List <Bus> busDingdan=new ArrayList<Bus>();
					busDingdan.add(bus.get((index - 1) * 5 + choice - 1));
					String choiceContent1 = HtmlCenter.getInstance()
							.getHtmlByList("bus.vm",
									busDingdan,
									"inputs", "videohtml");
					responseDisplay.setDataType("1");
					responseDisplay.setHeight("0.8");
					responseDisplay.setContent(choiceContent1);
					rma.setDisplayText(JSON.toJSONString(responseDisplay));
					return rma;
				}

				String choiceContent = HtmlCenter.getInstance().getHtmlByList(
						"bus.vm", bus.subList((index - 1) * 5, bus.size()),
						"inputs", "videohtml");
				responseDisplay.setDataType("1");
				responseDisplay.setHeight("0.8");
				responseDisplay.setContent(choiceContent);
				rma.setDisplayText(JSON.toJSONString(responseDisplay));
				return rma;
			}
		}
		return rma;
	}
}
