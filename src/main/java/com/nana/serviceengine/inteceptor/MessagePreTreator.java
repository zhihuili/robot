package com.nana.serviceengine.inteceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.common.util.ListDeepCopyer;
import com.nana.serviceengine.grammer.analyzer.GrammerAnalyzer;
import com.nana.serviceengine.grammer.bean.GrammerItem;

/**
 * 消息预处理
 * @author wds
 *
 */
public class MessagePreTreator {
	
	private static MessagePreTreator ppt = new MessagePreTreator();

	private MessagePreTreator() {
	}

	public static MessagePreTreator getInstance() {
		return ppt;
	}
	
	public String[] getDomainKeys(String[] domainKeyWords){
		try{
			List<String> domains = new ArrayList<String>();
			for(int i = 0;i<domainKeyWords.length;i++){
				DomainKeyWord tmp  = DomainDic.domainKeyWord.get(domainKeyWords[i]);
				if(tmp!=null){
					domains.add(tmp.getDomain());
				}
			}
			return domains.toArray(new String[]{});
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 分析语法结构 目前只分析了否定语、宾语和其的定语和连词 需完善
	 * @param terms
	 * @return
	 */
	public GrammerItem getGrammerItem(List<Term> terms){
		GrammerItem gi = new GrammerItem();
	    GrammerAnalyzer.getInstance().analysisAll(gi, terms);
		return gi;
	}
	/**
	 * 删除标点符号 并返回句子中名词的数量
	 * 
	 * @param terms
	 * @return
	 */
	private int deleWTag(List<Term> terms) {
		int count = 0;
		Iterator it = terms.iterator();
		while (it.hasNext()) {
			Term t = (Term) it.next();
			if ("w".equals(t.getNatureStr())) {
				it.remove();
			}
			if ("n".equals(t.getNatureStr())) {
				count++;
			}
		}
		return count;
	}
	
	private void deleUJWords(List<Term> terms){
		Iterator it = terms.iterator();
		while (it.hasNext()) {
			Term t = (Term) it.next();
			if ("uj".equals(t.getNatureStr())) {
				it.remove();
			}
		}
	}
	
	

}
