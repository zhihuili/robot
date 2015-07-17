package com.nana.serviceengine.domain.joke.daomainparam;

import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.itemcollector.CityCollector;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.domain.joke.bean.Joke;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

public class JokeParam extends DomainParam<Joke> {
	@Override
	protected void setParams() {
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
		params.put(indexChange.getName(), indexChange);
	}
}
