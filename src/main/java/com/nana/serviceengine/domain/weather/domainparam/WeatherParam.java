package com.nana.serviceengine.domain.weather.domainparam;

import java.util.Date;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.domain.itemcollector.CityCollector;
import com.nana.serviceengine.domain.itemcollector.GPSCollector;
import com.nana.serviceengine.domain.itemcollector.TimeCollector;
import com.nana.serviceengine.domain.weather.bean.WeatherFuture;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class WeatherParam extends DomainParam<WeatherFuture> {

	@Override
	protected void setParams() {
		ParamItem location = new ParamItem();
		location.setName("location");
		location.setCollector(CityCollector.getInstance());
		location.setAlertMes("请问您想知道什么地方的天气？");
		location.setNeedExternalLoad(true);
		location.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null)
					return item.getCollectResult();
				return null;
			}
		});
		ParamItem date = new ParamItem();
		date.setName("date");
		date.setCollector(TimeCollector.getInstance());
		date.setNeedExternalLoad(false);
		date.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null) {
					return item.getCollectResult();
				} else {
					return new Date[]{new Date()};
				}
			}

		});
		ParamItem gps = new ParamItem();
		gps.setName("gps");
		gps.setCollector(GPSCollector.getInstance());
		gps.setNeedExternalLoad(true);
		gps.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null) {
					return item.getCollectResult();
				} else {
					return new GPS();
				}
			}

		});

		params.put(location.getName(), location);
		params.put(date.getName(), date);
		//params.put(gps.getName(), gps);
	}

	

}
