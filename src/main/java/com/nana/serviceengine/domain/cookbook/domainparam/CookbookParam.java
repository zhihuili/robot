package com.nana.serviceengine.domain.cookbook.domainparam;
import com.nana.serviceengine.domain.cookbook.bean.Cookbook;
import com.nana.serviceengine.domain.itemcollector.BusStartStationCollector;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
public class CookbookParam extends DomainParam<Cookbook> {
	@Override
	protected void setParams() {
		ParamItem cookbookKey=new ParamItem();
		cookbookKey.setName("cookbook");
		cookbookKey.setAlertMes("请告诉我您想炒什么菜");
		cookbookKey.setCollector(BusStartStationCollector.getInstance());//收集器 
		cookbookKey.setNeedExternalLoad(true);
		cookbookKey.setCmd(new ParamCommand() {
			@Override
			public Object doProcess(ParamItem item) {
				if (item.getCollectResult() != null)
					return ((String[])item.getCollectResult())[0];
				return null;
			}
		});
		params.put(cookbookKey.getName(), cookbookKey);
	}
}
