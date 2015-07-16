package com.nana.serviceengine.domain.food.domainparam;

import com.nana.serviceengine.domain.food.bean.Foods;
import com.nana.serviceengine.domain.itemcollector.GPSCollector;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class FoodParam extends DomainParam<Foods> {

	@Override
	protected void setParams() {
		// TODO Auto-generated method stub
		ParamItem gps = new ParamItem();
		gps.setName("gps");
		gps.setCollector(GPSCollector.getInstance());
		gps.setCmd(new ParamCommand() {
			
			@Override
			public Object doProcess(ParamItem item) {
				if(item.getCollectResult() != null)
					return item.getCollectResult();
				return null;
			}
		});
	}

}
