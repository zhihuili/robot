package com.nana.serviceengine.domain.location.responsecreator;

import java.util.List;
import java.util.Map;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.location.bean.Position;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class LocationSentenceCreator implements SentenceCreator {
	private static LocationSentenceCreator csr = new LocationSentenceCreator();

	private LocationSentenceCreator() {

	}

	public static LocationSentenceCreator getInstance() {
		return csr;
	}

	public String createSentence(Map<String, Object> params) {
		return null;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		// TODO Auto-generated method stub
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String, ParamItem> paramItems = params.getParams();
		List<Position> data = params.getResult();
		String res = null;

		if (data == null && data.size() == 0) {
			rma.setAudioText("哎呀，没有数据。");
			return rma;
		}else{
			res="地址:"+data.get(0).getAddress() +" "+"标志:"+data.get(0).getBusiness();
		}
		rma.setAudioText(res);
		return rma;
	}
}
