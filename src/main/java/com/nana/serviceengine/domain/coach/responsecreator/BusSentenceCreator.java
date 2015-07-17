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
	private String mStr="mStr";
	Map<String,List<Bus>> map = new HashMap<String,List<Bus>>();
	private int i=1;
	private BusSentenceCreator() {

	}

	public static BusSentenceCreator getInstance() {
		return wsc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<Bus> allflights = params.getResult();
		// 设置元素的下标
				for (int i = 0; i < allflights.size(); i++) {
					allflights.get(i).setIndex(i + 1);
				}

				String res = null;
				if (allflights == null && allflights.size() == 0) {
					rma.setAudioText("哎呀，没有你想要汽车信息。");
					return rma;
				}
				// 获取第几页
				Integer index = (Integer) paramItems.get("indexChange").getValue();

				// 分页的启示值,每页显示5个
				int start = (index - 1) * 5;
				int end = index * 5;
				// 获取结果
				String alert = params.getResult(allflights, start+1);
				// 设置提示的话语
				rma.setAudioText(alert);
				if (index * 5 > allflights.size())
					end = allflights.size() - 1;
				List<Bus> flights = allflights.subList(start, end);
				// 重新设置下标为1-5
				for (int i = 0; i < flights.size(); i++) {
					flights.get(i).setIndex(i + 1);
				}
		       
				// 获取用户的选择的那一航班信息
				int choice = (Integer) paramItems.get("choice").getValue();
				if (choice != -1) {// 搜集到了用户的选择
					if (choice >= 1 && choice <= 5) {// 在选择范围
						int choose = start + choice - 1;
						if (choose < allflights.size()) {// 在搜索的航班中数据
							List<Bus> order = new ArrayList<Bus>();
							order.add(allflights.get(choose));
							ResponseDisplay responseDisplay = new ResponseDisplay();
							responseDisplay.setDataType("1");
							responseDisplay.setHeight("0.2");
							responseDisplay.setContent(HtmlCenter.getInstance()
									.getHtmlByList("bus.vm", order, "inputs",
											"demandhtml"));
							rma.setDisplayText(JSON.toJSONString(responseDisplay));
							return rma;
						} else {
							rma.setAudioText("对不起，没有找到您想要的下单的汽车信息");
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
					responseDisplay.setHeight("0.8");
					responseDisplay.setContent(HtmlCenter.getInstance().getHtmlByList(
							"bus.vm", flights, "inputs", "videohtml"));
					rma.setDisplayText(JSON.toJSONString(responseDisplay));
				}
		 		return rma;
			}
		}
