package com.nana.serviceengine.grammer.analyzer;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.dic.DomainDic;
import com.nana.serviceengine.grammer.bean.GrammerItem;
import com.nana.serviceengine.grammer.bean.ObjectItem;

public class GrammerAnalyzer {

	private static GrammerAnalyzer aba = new GrammerAnalyzer();

	private GrammerAnalyzer() {
	}

	public static GrammerAnalyzer getInstance() {
		return aba;
	}

	public void analysisAll(GrammerItem gi, List<Term> terms) {
		analysisObject(gi, terms);

		if (gi.getObjects().size() == 0)
			return;
		ObjectItem oi = gi.getObjects().get(0);
		int endIndex = oi == null ? 0 : oi.getConnectIndex();
		if (endIndex != 0)
			analysisNotItem(gi, terms, endIndex);
		else
			analysisNotItem(gi, terms);
	}

	/**
	 * 分析宾语
	 * 
	 * @param terms
	 * @return
	 */
	public void analysisObject(GrammerItem gi, List<Term> terms) {
		SentenceRuleAnalyzer.getInstance().analyObjects(gi, terms);
	}

	/**
	 * 分析主语
	 * 
	 * @param gi
	 */
	public void analysisSub(GrammerItem gi, List<Term> terms) {

	}

	/**
	 * 获得否定词并设置在gi中
	 * 
	 * @param gi
	 * @param terms
	 */
	public void analysisNotItem(GrammerItem gi, List<Term> terms) {
		for (Term term : terms) {
			if (DomainDic.domainKeyWord.get(term.getRealName()) == null) {
				if ("not"
						.equals(DomainDic.domainKeyWord.get(term.getRealName()))) {
					gi.setNotItem(term.getRealName());
					return;
				}
			}
		}
	}

	/**
	 * 指定搜索终点index来设置否定词
	 * 
	 * @param gi
	 * @param terms
	 * @param endIndex
	 */
	public void analysisNotItem(GrammerItem gi, List<Term> terms, int endIndex) {
		for (int i = 0; i < endIndex; i++) {
			if (DomainDic.domainKeyWord.get(terms.get(i).getRealName()) == null) {
				if ("not".equals(DomainDic.domainKeyWord.get(terms.get(i)
						.getRealName()))) {
					gi.setNotItem(terms.get(i).getRealName());
					return;
				}
			}
		}
	}

}
