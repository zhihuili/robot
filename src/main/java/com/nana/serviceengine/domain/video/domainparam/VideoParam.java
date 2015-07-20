package com.nana.serviceengine.domain.video.domainparam;

import com.nana.serviceengine.domain.itemcollector.AttriCollector;
import com.nana.serviceengine.domain.itemcollector.PageTurnCollector;
import com.nana.serviceengine.domain.video.bean.Movie;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamCommand;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;

/**
 * 电影搜索参数
 * 
 * @author wds
 *
 */
public class VideoParam extends DomainParam<Movie> {


	@Override
	protected void setParams() {
		ParamItem keyWord = new ParamItem();
		keyWord.setAlertMes("请问您想看有关什么的电影？");
		keyWord.setCollector(AttriCollector.getInstance());
		keyWord.setName("keyWord");
		keyWord.setNeedExternalLoad(true);
		keyWord.setCmd(new ParamCommand() {

			@Override
			public Object doProcess(ParamItem keyWord) {
				if (keyWord.getCollectResult() != null)
					return keyWord.getCollectResult();
				return null;
			}

		});
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
					old += tmp;
					return new Integer(old);
				}

			}

		});
		params.put(keyWord.getName(), keyWord);
		params.put(indexChange.getName(), indexChange);
	}

}
