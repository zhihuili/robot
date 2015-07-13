package com.nana.serviceengine.domain.flight.responsecreator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.flight.bean.Flight;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class FlightSentenceCreator implements SentenceCreator {

	private static final FlightSentenceCreator flightSentenceCreator = new FlightSentenceCreator();

	private FlightSentenceCreator() {
		// TODO Auto-generated constructor stub
	}

	public static FlightSentenceCreator getinstance() {
		return flightSentenceCreator;
	}

	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
       ResponseMessageAdapter rma = new ResponseMessageAdapter();
       Map<String,ParamItem> paramItems = params.getParams();
	   List<Flight> data = params.getResult();
	   String res = null;
	   
	   if(data == null && data.size() == 0){
			rma.setAudioText("哎呀，没有您想要航班数据。");
			return rma;
		}
	   //$flight.starttime $flight.depcity $flight.flightNum $flight.arrtime  $flight.arrcity
	   if (data != null) {
		   Properties props = new Properties();
	        props.put(".", ".\\vm");
	        VelocityEngine ve = new VelocityEngine(props);
	        // 配置引擎上下文对象
	        VelocityContext ctx = new VelocityContext();
	        ctx.put("action", "./submit");
	        ArrayList<HashMap<String, String>>  flights = new ArrayList<HashMap<String,String>>();
	       
	        for(int i = 0; i < data.size(); i++){
	        	 HashMap<String, String> flight = new HashMap<String, String>();
	        	 flight.put("starttime", data.get(i).getDepTime());
	        	 flight.put("depcity", data.get(i).getDepcity());
	        	 flight.put("flightNum", data.get(i).getFlightNum());
	        	 flight.put("arrtime", data.get(i).getArrTime());
	        	 flight.put("arrcity", data.get(i).getArrcity());
	        	 flights.add(flight);
	        }
	        ctx.put("flights",flights);
	        // 加载模板文件
	        Template t = ve.getTemplate("frm.vm");
	        StringWriter sw = new StringWriter();
	        // 渲染模板
	        t.merge(ctx, sw);
	        System.out.print(sw.toString());
	        rma.setDisplayText(sw.toString());
	   }
	   //rma.setAudioText(res);
	   return rma;
	}
}
