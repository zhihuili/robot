package com.nana.serviceengine.domain.joke.responsecreator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.domain.coach.bean.Bus;
import com.nana.serviceengine.domain.coach.bean.Coach;
import com.nana.serviceengine.domain.commonapi.htmlcenter.HtmlCenter;
import com.nana.serviceengine.domain.joke.bean.Joke;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.responsecreator.SentenceCreator;

public class JokeSentenceCreator implements SentenceCreator {
	
	private static JokeSentenceCreator wsc = new JokeSentenceCreator();

	private JokeSentenceCreator() {

	}

	public static JokeSentenceCreator getInstance() {
		return wsc;
	}
	@Override
	public ResponseMessageAdapter createSentence(DomainParam params) {
		ResponseMessageAdapter rma = new ResponseMessageAdapter();
		Map<String,ParamItem> paramItems = params.getParams();
		List<Joke> list=new ArrayList<Joke>();
		List<Joke> data = params.getResult();
		String pesponse="";
		String content=null;
		if(data == null && data.size() == 0){
			rma.setAudioText("不好意思，没有查询到相关数据");
			return rma;
		}
		if(data!=null){
			    /**
			     *  joke：20条数据随机返回一个
			     */
			int i=new Random().nextInt(20)%(21) + 0;
			 content=(String) data.get(i).getContent().replace("?", "");
			//Joke joke=new Joke(content,data.get(i).getHashId(),data.get(i).getUnixtime(),data.get(i).getUpdatetime());
			Joke joke=new Joke();
			joke.setContent(content);
			joke.setHashId(data.get(i).getHashId());
			joke.setUnixtime(data.get(i).getUnixtime());
			joke.setUpdatetime(data.get(i).getUpdatetime());
			list.add(joke);
			System.out.println(joke.getContent());
			pesponse=HtmlCenter.getInstance().getHtmlByBean("joke.vm", joke,"videohtml");
			//pesponse=HtmlCenter.getInstance().getHtmlByList("joke.vm", joke, "inputs");
		}
		rma.setAudioText(content);
		return rma;
	}

}
