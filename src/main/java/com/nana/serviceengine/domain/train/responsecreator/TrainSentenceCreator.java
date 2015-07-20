package com.nana.serviceengine.domain.train.responsecreator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.domain.train.bean.Train;
import com.nana.serviceengine.inout.bean.ResponseDisplay;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class TrainSentenceCreator implements SentenceCreator {
	private static final TrainSentenceCreator trainSentenceCreator = new TrainSentenceCreator();

	private TrainSentenceCreator() {

	}

	public static TrainSentenceCreator getInstance() {
		return trainSentenceCreator;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		// TODO Auto-generated method stub
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String, ParamItem> paramItems = params.getParams();
		List<Train> allTrain = params.getResult();

		Date date = (Date) paramItems.get("date").getValue();
		// 设置元素的下标
		for (int i = 0; i < allTrain.size(); i++) {
			allTrain.get(i).setIndex(i + 1);
		}

		String res = null;
		if (allTrain == null || allTrain.size() == 0) {
			rma.setAudioText("很抱歉!没有找到你想要的列车信息。");
			return rma;
		}
		Integer index = (Integer) paramItems.get("indexChange").getValue();

		// 分页的启示值,每页显示5个
		int start = (index - 1) * 5;
		int end = index * 5;
		// 获取结果
		String alert = params.getResult(allTrain, start + 1);
		// 设置提示的话语
		rma.setAudioText(alert);
		if (index * 5 > allTrain.size())
			end = allTrain.size() - 1;
		List<Train> trains = allTrain.subList(start, end);
		// 重新设置下标为1-5
		for (int i = 0; i < trains.size(); i++) {
			trains.get(i).setIndex(i + 1);
		}
		// 获取用户的选择的那一班列车信息
		Integer choice = (Integer) paramItems.get("choice").getValue();

		if (choice != null && choice != -1) {
			if (choice >= 1 && choice <= 5) {
				int choose = start + choice - 1;
				if (choose < allTrain.size()) {
					List<Train> order = new ArrayList<Train>();
					order.add(allTrain.get(choose));
					ResponseDisplay responseDisplay = new ResponseDisplay();
					responseDisplay.setDataType("1");
					responseDisplay.setHeight("0.2");
					responseDisplay.setContent(HtmlCenter.getInstance()
							.getHtmlByList("trainfrm.vm", order, "trains",
									"demandhtml"));
					rma.setDisplayText(JSON.toJSONString(responseDisplay));
					return rma;
				} else {
					rma.setAudioText("对不起，没有找到您想要下单的列车信息");
					return rma;
				}
			} else {
				rma.setAudioText("当前选择无效，请您重新选择");
				return rma;
			}
		}

		if (trains != null) {
			ResponseDisplay responseDisplay = new ResponseDisplay();
			responseDisplay.setDataType("1");
			responseDisplay.setHeight("0.5");
			responseDisplay.setContent(HtmlCenter.getInstance().getHtmlByList(
					"trainfrm.vm", trains, "trains", "videohtml"));
			rma.setDisplayText(JSON.toJSONString(responseDisplay));
		}
		return rma;

	}

}
