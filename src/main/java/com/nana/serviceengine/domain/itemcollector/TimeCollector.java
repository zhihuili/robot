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
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class TimeCollector extends Collector<Date[]>{
	private static TimeCollector tc = new TimeCollector();
	private TimeCollector() {
	}
	public static TimeCollector getInstance() {
		return tc;
	}
	/**
	 * 过期 将时间关键词转化为机器能识别的时间
	 * @param input
	 * @return
	 */
	public Date[] parseDate(String input) {
		List<Date> dates = new ArrayList<Date>();
		List<String> timeMapLines = new TxtReader("resources/timemaper")
				.getContentLineList();
		if (timeMapLines != null && timeMapLines.size() != 0) {
			for (String item : timeMapLines) {
				String[] tmp = item.split(" ");
				if (input.contains(tmp[0])) {
					int diff = Integer.parseInt(tmp[1]);
					dates.add(new Date(new Date().getTime() + diff * 24 * 60 * 60 * 1000));
				}
			}
			String strDate = "";
			try {
				Pattern p = Pattern.compile("[\\d]{1,2}月[\\d]{1,2}");
				Matcher mather = p.matcher(input);
				if (mather.find())
					strDate = mather.group();
			} catch (Exception ex) {

			}
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd");
			try {
				if (strDate.matches("[\\d]{1,2}月[\\d]{1,2}"))
					dates.add(df.parse((new Date().getYear() + 1900) + "年"
							+ strDate));
			} catch (ParseException e) {
				e.printStackTrace();
				// log
			}
		}
		return dates.toArray(new Date[] {});
	}
	/**
	 * 目前识别有限需要补充几天后这种
	 * @param mes
	 * @return
	 */
	public Date[] parseDate(UserMessage mes) {
		List<Date> res = new ArrayList<Date>();
		for (Term term : mes.getTerms()) {
			try{
				DomainKeyWord keyWord = DomainDic.domainKeyWord.get(term
						.getRealName());
				if("time".equals(keyWord.getDomain())){
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
				
			}catch(Exception ex){
				continue;
			}
			
		}
		if (res.size() != 0)
			return res.toArray(new Date[] {});
		return null;
	}

	
	@Override
	public Date[] initCollectParam(UserMessage message,
			ServiceProcessor processor) {
		return parseDate(message);
	}
	@Override
	public Date[] lackCollectParam(UserMessage message,
			ServiceProcessor processor) {
		return parseDate(message);
	}
	@Override
	public Date[] finishCollectParam(ParamItem paramItem, UserMessage message,
			ServiceProcessor processor) {
		return parseDate(message);
	}
}
