package com.nana.serviceengine.domain.coach.daomainparam;

import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.itemcollector.BusEndStationCollector;
import com.nana.serviceengine.domain.itemcollector.BusStartStationCollector;
import com.nana.serviceengine.domain.itemcollector.CityCollector;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class BusParam extends DomainParam<Bus> {
	@Override
	protected void setParams() {
		
		ParamItem index=new ParamItem();
		index.setName("indexChange");
		index.setCollector(PageTurnCollector.getInstance());
		index.setNeedExternalLoad(false);
		index.setCmd(new ParamCommand() {
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
		
		
		ParamItem starStation=new ParamItem();
		starStation.setName("start");
		starStation.setAlertMes("请告诉我您要查询汽车站的出发点");
		starStation.setCollector(BusStartStationCollector.getInstance());
		starStation.setNeedExternalLoad(true);
		starStation.setCmd(new ParamCommand() {
			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null)
					if(item.getCollectResult() != null && ((String[])item.getCollectResult()).length>0 && !((String[])item.getCollectResult())[0].equals(item.getValue()))
					return ((String[])item.getCollectResult())[0];
				return null;
			}
		});
		ParamItem endStation = new  ParamItem();
		endStation.setName("end");
		endStation.setCollector(BusEndStationCollector.getInstance());
		endStation.setAlertMes("请告诉我您要查询汽车站的终点");
		endStation.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null && ((String[])item.getCollectResult()).length>0)
				 return ((String[])item.getCollectResult())[0];
				return null;
			}
		});
		params.put(index.getName(), index);
		params.put(starStation.getName(), starStation);
		params.put(endStation.getName(), endStation);
	}
}
