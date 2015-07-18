package com.nana.serviceengine.domain.itemcollector;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class TimeSlotCollector implements Collector<String[]> {

	private static final TimeSlotCollector timeCollector = new TimeSlotCollector();

	private TimeSlotCollector() {
	}

	public static TimeSlotCollector getinstance() {
		return timeCollector;
	}

	public static String[] parsetime(List<Term> terms) {
		List<String> res = new ArrayList<String>();

		for (int i = 0; i < terms.size(); i++) {
			if ("t".equals(terms.get(i).getNatureStr())) {// 时间名词
				if ("上午".equals(terms.get(i).getRealName())
						|| "下午".equals(terms.get(i).getRealName())
						|| "早上".equals(terms.get(i).getRealName())
						|| "晚上".equals(terms.get(i).getRealName())
						|| "早晨".equals(terms.get(i).getRealName())
						|| "中午".equals(terms.get(i).getRealName())) {
					res.add(terms.get(i).getRealName());
				}
			}
		}
		if (res.size() != 0) {
			return res.toArray(new String[] {});
		}
		return res.toArray(new String[] {});
	}

	@Override
	public String[] getParam(ParamItem paramItem,UserMessage message, ServiceProcessor processor) {
		return parsetime(message.getTerms());
	}

}
