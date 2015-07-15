package com.nana.serviceengine.domain.location.daomainparam;

import com.nana.serviceengine.common.bean.GPS;
import com.nana.serviceengine.domain.itemcollector.GPSCollector;
import com.nana.serviceengine.domain.location.bean.Position;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class LocationParam extends DomainParam<Position> {

	@Override
	protected void setParams() {
		// TODO Auto-generated method stub
		ParamItem gps = new ParamItem();
		gps.setName("gps");
		gps.setAlertMes("您未打开GPS");
		gps.setCollector(GPSCollector.getInstance());
		gps.setNeedExternalLoad(true);
		gps.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null) {
					return item.getCollectResult();
				} 
				return null;
			}
		});

		params.put(gps.getName(), gps);

	}

}
