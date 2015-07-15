package com.nana.serviceengine.domain.flight.responsecreator;

import java.util.List;
import java.util.Map;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class FlightSentenceCreator implements SentenceCreator {

	private static final FlightSentenceCreator flightSentenceCreator = new FlightSentenceCreator();

	private FlightSentenceCreator() {
		// TODO Auto-generated constructor stub
	}

	public static FlightSentenceCreator getinstance() {
		return flightSentenceCreator;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
       ResponseMessageAdapter rma = new ResponseMessageAdapter();
       Map<String,ParamItem> paramItems = params.getParams();
	   List<Flight>  allflights = params.getResult();
	   //设置元素的下标
	   for(int i = 0; i < allflights.size(); i++){
		   allflights.get(i).setIndex(i+1);
	   }
	
	   String res = null;
	   if(allflights == null && allflights.size() == 0){
			rma.setAudioText("哎呀，没有数据。");
			return rma;
	    }
	   Integer index = (Integer) paramItems.get("indexChange").getValue();
		// 获取结果
		String alert = params.getResult(allflights, index);
		// 设置提示的话语
		rma.setAudioText(alert);
		//分页的启示值,每页显示5个
		int start = (index-1)*5;
		int end  = index*5;
		if(index*5 > allflights.size())
			end = allflights.size()-1;
	    List<Flight> flights = allflights.subList(start,end);
	    	
	   if (flights != null) {
		   res = HtmlCenter.getInstance().getHtmlByList("flightfrm.vm", flights, "flights");
		}
	   rma.setDisplayText(res);
	   //rma.setAudioText(res);
	   return rma;
	}
}
