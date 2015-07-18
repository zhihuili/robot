package com.nana.serviceengine.domain.itemcollector;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

/**
 * 该收集器是针对用户查询某地的汽车站信息
 * 
 * @author luhuanju
 *
 */
public class BusEndStationCollector implements Collector<String[]> {
	private static BusEndStationCollector cc = new BusEndStationCollector();

	public static BusEndStationCollector getInstance() {
		return cc;
	}

	@Override
	public String[] getParam(ParamItem paramItem,UserMessage message, ServiceProcessor processor) {
		return parseCity(message.getTerms());
	}

	public String[] parseCity(List<Term> terms) {
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < terms.size(); i++) {

			if (terms.get(i).getRealName().equals("到")
					&& (i + 1) < terms.size()||terms.get(i).getRealName().equals("到达")
					&& (i + 1) < terms.size()||terms.get(i).getRealName().equals("去")
					&& (i + 1) < terms.size()||terms.get(i).getRealName().equals("至")
					&& (i + 1) < terms.size()) {// 终点站
				DomainKeyWord dkw1 = DomainDic.domainKeyWord.get(terms.get(
						i + 1).getRealName());
				if ("address".equals(dkw1.getDomain())) {
					res.add(dkw1.getValue());
					i++;
				}
			}
		}
		
		if (res.size() != 0)
			return res.toArray(new String[] {});

		return null;
	}
}
