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
import com.nana.serviceengine.statemachine.bean.ParamItemState;

/**
 * 该收集器是针对用户查询某地的汽车站信息
 * 
 * @author luhuanju
 *
 */
public class BusEndStationCollector extends Collector<String[]> {
	private static BusEndStationCollector cc = new BusEndStationCollector();

	public static BusEndStationCollector getInstance() {
		return cc;
	}

	public String[] parseCity(ParamItem paramItem, List<Term> terms) {
		List<String> res = new ArrayList<String>();

		if (paramItem.getParamItemState().equals(ParamItemState.FINISH)
				|| paramItem.getParamItemState().equals(ParamItemState.INIT)) {// 数据缺失
			for (int i = 0; i < terms.size(); i++) {
				if (terms.get(i).getRealName().equals("到")
						&& (i + 1) < terms.size()
						|| terms.get(i).getRealName().equals("到达")
						&& (i + 1) < terms.size()
						|| terms.get(i).getRealName().equals("去")
						&& (i + 1) < terms.size()
						|| terms.get(i).getRealName().equals("至")
						&& (i + 1) < terms.size()) {// 终点站
					DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms.get(
							i + 1).getRealName());
					if (dkw == null)
						continue;
					if ("address".equals(dkw.getDomain())) {
						res.add(dkw.getValue());
						i++;
					}
				}
			}
		}

		if (res.size() != 0)
			return res.toArray(new String[] {});

		return null;
	}

	@Override
	public String[] initCollectParam(UserMessage message,
			ServiceProcessor processor) {
		List<Term> terms = message.getTerms();
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getRealName().equals("到")
					&& (i + 1) < terms.size()
					|| terms.get(i).getRealName().equals("到达")
					&& (i + 1) < terms.size()
					|| terms.get(i).getRealName().equals("去")
					&& (i + 1) < terms.size()
					|| terms.get(i).getRealName().equals("至")
					&& (i + 1) < terms.size()) {// 终点站
				DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms
						.get(i + 1).getRealName());
				if (dkw == null)
					continue;
				if ("address".equals(dkw.getDomain())) {
					res.add(dkw.getValue());
					i++;
				}
			}
		}
		return res.toArray(new String[] {});
	}

	@Override
	public String[] lackCollectParam(UserMessage message,
			ServiceProcessor processor) {
		List<Term> terms = message.getTerms();
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < terms.size(); i++) {
			DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms.get(i)
					.getRealName());
			if (dkw == null)
				continue;
			if ("address".equals(dkw.getDomain())) {// 判断是否是一个地名
				res.add(dkw.getValue());
				return res.toArray(new String[] {});
			}
		}
		return null;
	}

	@Override
	public String[] finishCollectParam(ParamItem paramItem,
			UserMessage message, ServiceProcessor processor) {
		List<Term> terms = message.getTerms();
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getRealName().equals("到")
					&& (i + 1) < terms.size()
					|| terms.get(i).getRealName().equals("到达")
					&& (i + 1) < terms.size()
					|| terms.get(i).getRealName().equals("去")
					&& (i + 1) < terms.size()
					|| terms.get(i).getRealName().equals("至")
					&& (i + 1) < terms.size()) {// 终点站
				DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms
						.get(i + 1).getRealName());
				if (dkw == null)
					continue;
				if ("address".equals(dkw.getDomain())) {
					res.add(dkw.getValue());
					i++;
				}
			}
		}
		return res.toArray(new String[] {});
	}
}
