package com.nana.serviceengine.domain.coach.daomainparam;

import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.itemcollector.CityCollector;
import com.nana.serviceengine.domain.itemcollector.BusEndStationCollector;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class BusParam extends DomainParam<Bus> {
	@Override
	protected void setParams() {
		ParamItem starStation=new ParamItem();
		starStation.setName("start");
		starStation.setAlertMes("请告诉我您要查询汽车站的出发点");
		starStation.setCollector(BusEndStationCollector.getInstance());
		starStation.setNeedExternalLoad(true);
		starStation.setCmd(new ParamCommand() {
			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null)
					return item.getCollectResult();
				return null;
			}
		});
		ParamItem endStation = new  ParamItem();
		endStation.setName("end");
		endStation.setCollector(CityCollector.getInstance());
		endStation.setAlertMes("请告诉我您要查询汽车站的终点");
		endStation.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null)
				 return item.getCollectResult();
				return null;
			}
		});
		params.put(starStation.getName(), starStation);
		params.put(endStation.getName(), endStation);
	}
}
