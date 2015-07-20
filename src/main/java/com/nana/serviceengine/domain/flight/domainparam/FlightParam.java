package com.nana.serviceengine.domain.flight.domainparam;

import java.awt.Choice;
import java.util.Date;

import javax.swing.GroupLayout.ParallelGroup;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.domain.itemcollector.BusEndStationCollector;
import com.nana.serviceengine.domain.itemcollector.BusStartStationCollector;
import com.nana.serviceengine.domain.itemcollector.ChoiceCollector;
import com.nana.serviceengine.domain.itemcollector.CityCollector;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.domain.itemcollector.TimeCollector;
import com.nana.serviceengine.domain.itemcollector.TimeSlotCollector;
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
		indexChange.setClearWhenExLoad(true);
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
					old += tmp;
					return new Integer(old);
				}
			}
		});
		
		ParamItem originStation = new ParamItem();
		originStation.setName("start");
		originStation.setCollector(BusStartStationCollector.getInstance());
		originStation.setAlertMes("请问您想从哪里出发");
		originStation.setNeedExternalLoad(true);
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
		endStation.setAlertMes("请问您想到的目的地");
		endStation.setNeedExternalLoad(true);
		
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
		date.setCollector(TimeCollector.getInstance());
		date.setNeedExternalLoad(true);
		date.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null)
				 return ((Date[])item.getCollectResult())[0];
				return new Date();
			}
		});
		
		//搜集时间段
		ParamItem timeslot = new ParamItem();
		timeslot.setName("timeslot");
		timeslot.setCollector(TimeSlotCollector.getinstance());
		timeslot.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null && ((String[])item.getCollectResult()).length>0){
					return ((String[])item.getCollectResult())[0];
				}
				return null;
			}
		});
		
	   //获取用户选择第几条航班信息
		ParamItem choice = new ParamItem();
        choice.setName("choice");		
		choice.setCollector(ChoiceCollector.getInstance());
		choice.setClearWhenExLoad(true);
		choice.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				 if(item.getCollectResult() != null && (Integer)item.getCollectResult() != -1){
					 return item.getCollectResult() ;
				 }
				return -1;
			}
		});
		
		params.put(indexChange.getName(), indexChange);
		params.put(originStation.getName(), originStation);
		params.put(endStation.getName(), endStation);
		params.put(date.getName(), date);
		params.put(choice.getName(), choice);
	    //params.put(timeslot.getName(), timeslot);
	}
}
