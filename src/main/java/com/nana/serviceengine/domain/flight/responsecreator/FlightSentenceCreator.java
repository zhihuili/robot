package com.nana.serviceengine.domain.flight.responsecreator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
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
	   List<Flight> data = params.getResult();
	   String res = null;
	   
	   if(data == null && data.size() == 0){
			rma.setAudioText("哎呀，没有数据。");
			return rma;
		}
	     
	   if (data != null) {
			Date starttime = new Date(data.get(0).getDepTime());
			Date arrtime = new Date(data.get(0).getArrTime());
			res = "航班的班次" + data.get(0).getFlightNum() + "目的地"
					+ data.get(0).getArrcity()
					+ data.get(0).getAirline() + "起飞时间"+
					starttime.getMonth()+"月"+starttime.getDay()+"日"+starttime.getHours()+"点"+starttime.getMinutes()+"分"
					+ data.get(0).getDepTime() + "到达时间"+
					arrtime.getMonth()+"月"+arrtime.getDay()+"日"+arrtime.getHours()+"点"+arrtime.getMinutes()+"分";
		}
	   rma.setAudioText(res);
	   return rma;
	}
}
