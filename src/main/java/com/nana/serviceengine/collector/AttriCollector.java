package com.nana.serviceengine.collector;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.dic.pool.DomainDic;
import com.nana.serviceengine.grammer.bean.ObjectItem;

public class AttriCollector {
	private static AttriCollector ac = new AttriCollector();
	private AttriCollector(){}
	
	public static AttriCollector getInstance(){
		return ac;
	}
	/**
	 * 获取一句话中的形容词和名词
	 * @param mes
	 * @return
	 */
	public String getAttributive(UserMessage mes){
		StringBuilder builder = new StringBuilder();
		for (Term term : mes.getTerms()) {
			char nature = term.getNatureStr().charAt(0);
			if ('n' == nature || 'a' == nature) {
				builder.append(term.getRealName() + " ");
			}
		}
		if (builder.length() != 0) 
			builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	/**
	 * 忽略领域关键词来获取关键词
	 * @param mes
	 * @param objectDomain
	 * @return
	 */
	public String deleteDomaintoGetAttri(UserMessage mes,String objectDomain){
		StringBuilder builder = new StringBuilder();
		for (Term term : mes.getTerms()) {
			DomainKeyWord tmpSubDomain = DomainDic.domainKeyWord
					.get(term.getRealName());
			//如果有领域关键词则忽略 不能忽略专有名词
			if( !"nz".equals(term.getNatureStr()) && tmpSubDomain != null && objectDomain.equals(tmpSubDomain.getDomain())) continue;
			char nature = term.getNatureStr().charAt(0);
			if ('n' == nature || 'a' == nature) {
				builder.append(term.getRealName() + " ");
			}
		}
		if (builder.length() != 0) 
			builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	/**
	 * 指定宾语的领域来找定语
	 * @param mes
	 * @param objectDomain
	 * @return
	 */
	public String getAttributive(UserMessage mes,String objectDomain){
		StringBuilder builder = new StringBuilder();
		List<ObjectItem> obItems = mes.getGrammerItem().getObjects();
		for (int i = 0; i < obItems.size(); i++) {
			DomainKeyWord tmpSubDomain = DomainDic.domainKeyWord
					.get(obItems.get(i).getValue());
			if (tmpSubDomain != null
					&& objectDomain.equals(tmpSubDomain.getDomain())) {
				for (String str : obItems.get(i).getAttributive()) {
					builder.append(str + " ");
				}
			}
		}
		if (builder.length() != 0) 
			builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
}
