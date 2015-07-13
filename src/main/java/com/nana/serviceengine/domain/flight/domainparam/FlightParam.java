package com.nana.serviceengine.domain.flight.domainparam;

import java.util.Date;

import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.domain.itemcollector.EndStationCollector;
import com.nana.serviceengine.domain.itemcollector.StartStationCollector;
import com.nana.serviceengine.domain.itemcollector.TimeCollector;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class FlightParam extends DomainParam<Flight>{

	@Override
	protected void setParams() {
		
		ParamItem originStation = new ParamItem();
		originStation.setName("start");
		originStation.setCollector(StartStationCollector.getInstance());
		originStation.setAlertMes("请问您想知道从哪里出发的航班");
		
		originStation.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				
				if(item.getCollectResult() != null)
					return item.getCollectResult();
				return null;
			}
		});
		
		ParamItem endStation = new  ParamItem();
		endStation.setName("end");
		endStation.setCollector(EndStationCollector.getinstance());
		endStation.setAlertMes("请问您想知道从哪里出发的航班");
		endStation.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null)
				 return item.getCollectResult();
				return null;
			}
		});
		
		ParamItem date = new  ParamItem();
		date.setName("date");
		date.setCollector(TimeCollector.getInstance());
		date.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null)
				 return item.getCollectResult();
				return new Date();
			}
		});
		params.put(originStation.getName(), originStation);
		params.put(endStation.getName(), endStation);
		params.put(date.getName(), date);
	}
}
