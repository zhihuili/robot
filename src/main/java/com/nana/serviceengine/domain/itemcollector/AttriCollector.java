package com.nana.serviceengine.domain.itemcollector;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.grammer.bean.GrammerItem;
import com.nana.serviceengine.grammer.bean.ObjectItem;
import com.nana.serviceengine.neuron.domainparam.bean.ParamItem;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.bean.DialogState;

public class AttriCollector implements Collector<String> {
	private static AttriCollector ac = new AttriCollector();

	private AttriCollector() {
	}

	public static AttriCollector getInstance() {
		return ac;
	}

	/**
	 * 获取一句话中的形容词和名词
	 * 
	 * @param mes
	 * @return
	 */
	public String getAttributive(UserMessage mes) {
		StringBuilder builder = new StringBuilder();
		for (Term term : mes.getTerms()) {
			char nature = term.getNatureStr().charAt(0);
			if ('n' == nature || 'a' == nature) {
				builder.append(term.getRealName() + " ");
			}
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length() - 1);
			return builder.toString();
		}
		return null;
	}

	public String getAttributive(UserMessage mes, int start, int end) {
		StringBuilder builder = new StringBuilder();
		for (int i = start; i < end; i++) {
			char nature = mes.getTerms().get(i).getNatureStr().charAt(0);
			if ('n' == nature || 'a' == nature) {
				builder.append(mes.getTerms().get(i).getRealName() + " ");
			}
		}
		if ("nz".equals(mes.getTerms().get(end).getNatureStr()))
			builder.append(mes.getTerms().get(end).getRealName() + " ");
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length() - 1);
			return builder.toString();
		}
		return null;
	}

	
	/**
	 * 忽略领域关键词来获取关键词
	 * 
	 * @param mes
	 * @param objectDomain
	 * @return
	 */
	public String deleteDomaintoGetAttri(UserMessage mes, String objectDomain) {
		StringBuilder builder = new StringBuilder();
		for (Term term : mes.getTerms()) {
			DomainKeyWord tmpSubDomain = DomainDic.domainKeyWord.get(term
					.getRealName());
			// 如果有领域关键词则忽略 不能忽略专有名词
			if (!"nz".equals(term.getNatureStr()) && tmpSubDomain != null
					&& objectDomain.equals(tmpSubDomain.getDomain()))
				continue;
			char nature = term.getNatureStr().charAt(0);
			if ('n' == nature || 'a' == nature) {
				builder.append(term.getRealName() + " ");
			}
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length() - 1);
			return builder.toString();
		}
		return null;
	}

	/**
	 * 指定宾语的领域来找定语
	 * 
	 * @param mes
	 * @param objectDomain
	 * @return
	 */
	public String getAttributive(UserMessage mes, String objectDomain) {
		StringBuilder builder = new StringBuilder();
		List<ObjectItem> obItems = mes.getGrammerItem().getObjects();
		for (int i = 0; i < obItems.size(); i++) {
			DomainKeyWord tmpSubDomain = DomainDic.domainKeyWord.get(obItems
					.get(i).getValue());
			if (tmpSubDomain != null
					&& objectDomain.equals(tmpSubDomain.getDomain())) {
				for (String str : obItems.get(i).getAttributive()) {
					builder.append(str + " ");
				}
			}
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length() - 1);
			return builder.toString();
		}
		return null;
	}

	/**
	 * 获取定语，如果是多领域处理，则按顺序来处理，处理完之后要删除对应的宾语
	 */
	@Override
	public String getParam(ParamItem paramItem,UserMessage message,ServiceProcessor processor) {
		UserDialog userDialog = UserTheme.UserDialog.get(message.getUserid());
		switch(userDialog.getState()){
			case START:{
				GrammerItem gi = message.getGrammerItem();
				ObjectItem oi = gi.getObjects().get(0);
				return getAttributive(message, oi.getConnectIndex(), oi.getIndex());
			}
			default:{
				if(processor == null) 
					return getAttributive(message);
				else
					return deleteDomaintoGetAttri(message,processor.getDomainKeyWord());
			}	
		}
	}
}
