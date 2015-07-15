package com.nana.serviceengine.domain.itemcollector;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.common.util.TxtReader;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class TimeCollector implements Collector<Date[]>{
	private static TimeCollector tc = new TimeCollector();

	private TimeCollector() {
	}

	public static TimeCollector getInstance() {
		return tc;
	}

	/**
	 * 目前识别有限需要补充几天后这种
	 * 
	 * @param mes
	 * @return
	 */
	public Date[] parseDate(UserMessage mes) {
		List<Date> res = new ArrayList<Date>();
		for (Term term : mes.getTerms()) {
			if ("t".equals(term.getNatureStr())) {
				DomainKeyWord keyWord = DomainDic.domainKeyWord.get(term
						.getRealName());
				// 如果词典中没有收集这个时间关键词则忽略它
				if (keyWord == null)
					continue;
				int diff = Integer.parseInt(keyWord.getValue());
				if (term.getRealName().contains("星期")
						|| term.getRealName().contains("周")) {
					int now = new Date().getDay();
					int tmp = diff > now ? diff - now : diff + 7 - now;
					res.add(new Date(new Date().getTime() + tmp * 24 * 60 * 60
							* 1000));
				} else {
					res.add(new Date(new Date().getTime() + diff * 24 * 60 * 60
							* 1000));
				}
			}
		}
		if (res.size() != 0)
			return res.toArray(new Date[] {});
		return null;
	}
	
	

	@Override
	public Date[] getParam(UserMessage message,ServiceProcessor processor) {
		return parseDate(message);
	}
}
