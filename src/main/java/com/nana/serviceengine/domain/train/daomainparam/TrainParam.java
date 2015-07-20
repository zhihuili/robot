package com.nana.serviceengine.domain.train.daomainparam;

import java.util.Date;

import com.nana.serviceengine.domain.itemcollector.AttriCollector;
import com.nana.serviceengine.domain.itemcollector.BusEndStationCollector;
import com.nana.serviceengine.domain.itemcollector.BusStartStationCollector;
import com.nana.serviceengine.domain.itemcollector.ChoiceCollector;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.domain.itemcollector.TimeCollector;
import com.nana.serviceengine.domain.train.bean.Train;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class TrainParam extends DomainParam<Train> {

	@Override
	protected void setParams() {
		// TODO Auto-generated method stub
		// 记录分页显示
		ParamItem indexChange = new ParamItem();
		indexChange.setCollector(PageTurnCollector.getInstance());
		indexChange.setName("indexChange");
		indexChange.setNeedExternalLoad(false);
		indexChange.setCmd(new ParamCommand() {
			@Override
			public Object doProcess(ParamItem item) {
				Integer old = (Integer) item.getValue();
				if (old == null) {
					old = new Integer(1);
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

		ParamItem startStation = new ParamItem();
		startStation.setName("start");
		startStation.setCollector(BusStartStationCollector.getInstance());
		startStation.setAlertMes("请告诉我您的出发站?");
		startStation.setNeedExternalLoad(true);
		startStation.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				// TODO Auto-generated method stub
				if (item.getCollectResult() != null
						&& ((String[]) item.getCollectResult()).length > 0
						&& !((String[]) item.getCollectResult())[0].equals(item
								.getValue()))
					return ((String[]) item.getCollectResult())[0];
				return null;
			}
		});

		ParamItem endStation = new ParamItem();
		endStation.setName("end");
		endStation.setCollector(BusEndStationCollector.getInstance());
		endStation.setAlertMes("请告诉我您的目的站?");
		endStation.setNeedExternalLoad(true);
		endStation.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				// TODO Auto-generated method stub
				if (item.getCollectResult() != null
						&& ((String[]) item.getCollectResult()).length > 0
						&& !((String[]) item.getCollectResult())[0].equals(item
								.getValue()))
					return ((String[]) item.getCollectResult())[0];
				return null;
			}
		});

		ParamItem date = new ParamItem();
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

		ParamItem trainType = new ParamItem();
		trainType.setName("type");
		trainType.setCollector(AttriCollector.getInstance());
		trainType.setNeedExternalLoad(true);
		trainType.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				// TODO Auto-generated method stub
				if (item.getCollectResult() != null) {
					return item.getCollectResult();
				}
				return null;
			}
		});
		
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
		params.put(startStation.getName(), startStation);
		params.put(endStation.getName(), endStation);
		params.put(date.getName(), date);
		params.put(trainType.getName(), trainType);
		params.put(choice.getName(), choice);
	}

}
