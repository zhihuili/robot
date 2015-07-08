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

	// @Override
	// public String dataCollectFinish(UserMessage mes) {
	// Integer indexChange = PageTurnCollector.getInstance().getIndexChange(
	// mes);
	// String attri = AttriCollector.getInstance().deleteDomaintoGetAttri(mes,
	// "video");
	// StringBuilder builder = new StringBuilder();
	// if(indexChange != null){
	// index += indexChange;
	// if(index < 1){
	// builder.append("上面没有啦!");
	// index = 1;
	// }else if(index>resMovie.size()){
	// builder.append("下面没有啦!");
	// }
	// else{
	// builder.append("好的。");
	// loadType = LoadType.INTERNALLOAD;
	// }
	// }
	// if(!"".equals(attri)){
	// builder.append("已为您查找新电影");
	// keyWord = attri;
	// index = 1;
	// loadType = LoadType.EXTERNALLOAD;
	// }
	//
	// return builder.toString();
	// }
	//
	// @Override
	// public String dataLack(UserMessage mes) {
	//
	// String attri = null;
	// if(count == 0){
	// attri = AttriCollector.getInstance().getAttributive(mes,"video");
	// }else{
	// attri = AttriCollector.getInstance().getAttributive(mes);
	// }
	// count++;
	// if("".equals(attri)){
	// loadType = LoadType.NOLOAD;
	// return "请问您喜欢看什么电影，说几个关键字就行。";
	// }else{
	// keyWord = attri;
	// loadType = LoadType.EXTERNALLOAD;
	// }
	// return "好的。";
	// }

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
					int value = (Integer) item.getValue();
					value += tmp;
					return new Integer(value);
				}

			}

		});
		params.put(keyWord.getName(), keyWord);
		params.put(indexChange.getName(), indexChange);
	}

}
