package com.nana.serviceengine.domain.weather.responsecreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.weather.bean.WeatherFuture;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class WeatherSentenceCreator implements SentenceCreator {

	private static WeatherSentenceCreator wsc = new WeatherSentenceCreator();

	private WeatherSentenceCreator() {

	}

	public static WeatherSentenceCreator getInstance() {
		return wsc;
	}


	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		//构造返回数据
		ResponseMessageAdapter rma =new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<WeatherFuture> data =params.getResult();
		if(data == null && data.size() == 0){
			rma.setAudioText("哎呀，没有数据。");
			return rma;
		}
		Date aimDate = ((Date[]) paramItems.get("date").getValue())[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String aimDateStr = sdf.format(aimDate);
		WeatherFuture res = null;
		for(WeatherFuture wf:data){
			if(aimDateStr.equals(wf.getDate())){
				res = wf;
				break;
			}
		}
		if(res == null){
			rma.setAudioText("我只能查到最近6天的天气！");
			return rma;
		}
		String tmp = ((String[])paramItems.get("location").getValue())[0] + (aimDate.getMonth()+1)+"月" + aimDate.getDate() +"日的天气情况如下：" + res.getWeather() + ", 风力："+res.getWind()
				+ ", 温度："+ res.getTemperature();
		rma.setAudioText(tmp);
		return rma;
	}

}
