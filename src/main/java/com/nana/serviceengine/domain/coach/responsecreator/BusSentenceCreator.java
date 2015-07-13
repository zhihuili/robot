package com.nana.serviceengine.domain.coach.responsecreator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class BusSentenceCreator implements SentenceCreator {
	
	private static BusSentenceCreator wsc = new BusSentenceCreator();

	private BusSentenceCreator() {

	}

	public static BusSentenceCreator getInstance() {
		return wsc;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<Bus> data = params.getResult();
		String pesponse="";
		if(data == null && data.size() == 0){
			rma.setAudioText("不好意思，没有查询到相关数据");
			return rma;
		}
		if(data!=null){
			// 输出html
			
			
			Properties props = new Properties();
			props.put(".", ".\\vm");
			VelocityEngine ve = new VelocityEngine(props);
			VelocityContext ctx = new VelocityContext();
			ctx.put("action", "./submit");
			ArrayList<HashMap<String, String>> inputs = new ArrayList<HashMap<String, String>>();
			for(int i=0;i<data.size();i++){
				HashMap<String, String> input2 = new HashMap<String, String>();
				input2.put("id", "brief");
				input2.put("start", data.get(i).getStart());
				input2.put("end", data.get(i).getArrive());
				input2.put("time", data.get(i).getDate());
				input2.put("price", data.get(i).getPrice());
				inputs.add(input2);
				
			}
			ctx.put("inputs", inputs);
			Template t = ve.getTemplate("bus.vm");
			StringWriter sw = new StringWriter();
			t.merge(ctx, sw);
			pesponse=sw.toString();
		}
		rma.setAudioText(pesponse);
		return rma;
	}

}
//Properties props = new Properties();
//props.put(".", ".\\vm");
//VelocityEngine ve = new VelocityEngine(props);
//// 配置引擎上下文对象
//VelocityContext ctx = new VelocityContext();
//ctx.put("action", "./submit");
//
//ArrayList<HashMap<String, String>> inputs = new ArrayList<HashMap<String, String>>();
//
//HashMap<String, String> input2 = new HashMap<String, String>();
//input2.put("id", "brief");
//input2.put("address", "客运站");
//input2.put("time", "09:30");
//input2.put("price", "34");
//inputs.add(input2);
//ctx.put("inputs", inputs);
//// 加载模板文件
//Template t = ve.getTemplate("caoch.vm");
//StringWriter sw = new StringWriter();
//// 渲染模板
//t.merge(ctx, sw);
//System.out.print(sw.toString());