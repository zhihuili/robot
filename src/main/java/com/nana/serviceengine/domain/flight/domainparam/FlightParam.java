package com.nana.serviceengine.domain.flight.domainparam;

import java.util.Date;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.domain.itemcollector.BusEndStationCollector;
import com.nana.serviceengine.domain.itemcollector.BusStartStationCollector;
import com.nana.serviceengine.domain.itemcollector.CityCollector;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class FlightParam extends DomainParam<Flight>{

	@Override
	protected void setParams() {
		//记录分页显示
		ParamItem indexChange = new ParamItem();
		indexChange.setCollector(PageTurnCollector.getInstance());
		indexChange.setName("indexChange");
		indexChange.setNeedExternalLoad(false);
		indexChange.setCmd(new ParamCommand() {
			@Override
			public Object doProcess(ParamItem item) {
				Integer old = (Integer)item.getValue();
				if (old == null){
					old =new Integer(1);
				}
				if (item.getCollectResult() == null) {
					return old;
				} else {
					int tmp = (Integer) item.getCollectResult();
					int value = (Integer) item.getValue();
					value += tmp;
					return new Integer(value);
				}
			}
		});
		
		ParamItem originStation = new ParamItem();
		originStation.setName("start");
		originStation.setCollector(BusStartStationCollector.getInstance());
		originStation.setAlertMes("请问您想知道从哪里出发的航班");
		
		originStation.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				
				if(item.getCollectResult() != null && ((String[])item.getCollectResult()).length>0 && !((String[])item.getCollectResult())[0].equals(item.getValue()))
					return ((String[])item.getCollectResult())[0];
				return null;
			}
		});
		
		ParamItem endStation = new  ParamItem();
		endStation.setName("end");
		endStation.setCollector(BusEndStationCollector.getInstance());
		endStation.setAlertMes("请问您想知道您的目的地的航班");
		endStation.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null && ((String[])item.getCollectResult()).length>0)
				 return ((String[])item.getCollectResult())[0];
				return null;
			}
		});
		
		ParamItem date = new  ParamItem();
		date.setName("date");
		date.setCollector(CityCollector.getInstance());
		date.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null)
				 return item.getCollectResult();
				return new Date();
			}
		});
		params.put(indexChange.getName(), indexChange);
		params.put(originStation.getName(), originStation);
		params.put(endStation.getName(), endStation);
		params.put(date.getName(), date);
	}
}
