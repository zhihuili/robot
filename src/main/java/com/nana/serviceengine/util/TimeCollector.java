package com.nana.serviceengine.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;

public class TimeCollector {
	/**
	 * 将时间关键词转化为机器能识别的时间
	 * 
	 * @param input
	 * @return
	 */
	public static Date[] parseDate(String input) {
		List<Date> dates = new ArrayList<Date>();
		List<String> timeMapLines = new TxtReader("resources/timemaper")
				.getContentLineList();
		if (timeMapLines != null && timeMapLines.size() != 0) {
			for (String item : timeMapLines) {
				String[] tmp = item.split(" ");
				if (input.contains(tmp[0])) {
					int diff = Integer.parseInt(tmp[1]);
					dates.add(new Date(new Date().getTime() + diff * 24 * 60 * 60
							* 1000));
				}
			}
			String strDate = "";
			try {
				Pattern p = Pattern.compile("[\\d]{1,2}月[\\d]{1,2}");
				Matcher mather = p.matcher(input);
				if(mather.find())
					strDate = mather.group();
			} catch (Exception ex) {

			}
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd");
			try {
				if(strDate.matches("[\\d]{1,2}月[\\d]{1,2}"))
					dates.add(df.parse((new Date().getYear() + 1900) + "年" + strDate));
			} catch (ParseException e) {
				e.printStackTrace();
				// log
			}
		}
		return dates.toArray(new Date[]{});
	}

	public static Date[] parseDate(List<Term> inputs) {
//		List<Date> res = new ArrayList<Date>();
//		for(int i=0;i<terms.size();i++){
//			if(terms.get(i).getNatureStr().equals("ns")){
//				res.add(terms.get(i).getRealName());
//			}
//		}
//		return (String[])res.toArray();
		
		return null;
	}
}
