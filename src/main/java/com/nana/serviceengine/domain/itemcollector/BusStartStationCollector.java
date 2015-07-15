package com.nana.serviceengine.domain.itemcollector;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.domain.commonapi.map.MapAPI;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class BusStartStationCollector implements Collector<String[]> {

	private static final BusStartStationCollector ssc = new BusStartStationCollector();

	private BusStartStationCollector() {

	}

	public static BusStartStationCollector getInstance() {
		return ssc;
	}

	/**
	 * 
	 * @param terms
	 *            根据分词结找到起始站和终点站
	 * @return
	 */
	public static String[] parsestation(UserMessage message) {
		List<String> res = new ArrayList<String>();
		List<Term> terms = message.getTerms();
		for (int i = 0; i < terms.size(); i++) {
			DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms.get(i)
					.getRealName());
			if (dkw == null)
				continue;

			DomainKeyWord dkw1 = DomainDic.domainKeyWord.get(terms.get(i)
					.getRealName());
			if (dkw1 != null && "address".equals(dkw1.getDomain())) {// 判断是否是一个地名

				if ((i - 1) < 0
					 || !("到".equals(terms.get(i - 1).getRealName()) || "去"
								.equals(terms.get(i - 1).getRealName()))) {
					res.add(dkw.getValue());
				}
			}
		}
		if (res.size() != 0) {
			return res.toArray(new String[] {});
		}
		//如果没有起始站信息，就默认起始站时gps定位的城市
		System.out.println(message.getGps());
		if(message.getGps() != null){
			String city = MapAPI.getInstance().getDetailInfoByGps(message.getGps())
					.getCity();
			DomainKeyWord citydkw = DomainDic.domainKeyWord.get(city);
			if(citydkw!=null &&"address".equals(citydkw.getDomain()) ){// 判断是否是一个地名
				res.add(city);
			}
		}
		return res.toArray(new String[]{});
	}

	@Override
	public String[] getParam(UserMessage message, ServiceProcessor processor) {
		return parsestation(message);
	}
}
