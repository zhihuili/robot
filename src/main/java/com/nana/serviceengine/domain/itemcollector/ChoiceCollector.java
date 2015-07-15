package com.nana.serviceengine.domain.itemcollector;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

/**
 * 当用户选择了选项时，此收集器用来收集用户选择了第几个
 * @author wds
 *
 */
public class ChoiceCollector implements Collector<Integer>{
	private static ChoiceCollector cc = new ChoiceCollector();

	private ChoiceCollector() {
	}

	public static ChoiceCollector getInstance() {
		return cc;
	}

	
	@Override
	public Integer getParam(UserMessage message, ServiceProcessor processor) {
		List<Term> terms = message.getTerms();
		boolean isHasChoice = false;
		boolean isHasNum = false;
		Integer index = null; 
		for(int i=0;i<terms.size();i++){
			Term term=terms.get(i);
			DomainKeyWord domain = DomainDic.domainKeyWord.get(term.getRealName());
			if(domain == null) continue;
			if("not".equals(domain.getDomain())) return null;
			if("want".equals(domain.getDomain()) || "choose".equals(domain.getDomain())) isHasChoice = true;
			if("number".equals(domain.getDomain()) ||"index".equals(domain.getDomain())) {
				isHasNum=true;
				index = Integer.parseInt(domain.getValue());
			}
		}
		if(isHasChoice && isHasNum){
			return index;
		}
		return null;
	}
	
	
}
