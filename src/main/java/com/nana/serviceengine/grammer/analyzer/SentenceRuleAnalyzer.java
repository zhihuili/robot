package com.nana.serviceengine.grammer.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;

import com.nana.serviceengine.grammer.bean.GrammerItem;
import com.nana.serviceengine.grammer.bean.ObjectItem;
import com.nana.serviceengine.grammer.bean.SentenceRuleItem;
import com.nana.serviceengine.grammer.dic.SentenceRule;

public class SentenceRuleAnalyzer {
	private static SentenceRuleAnalyzer sra = new SentenceRuleAnalyzer();

	private SentenceRuleAnalyzer() {
	}

	public static SentenceRuleAnalyzer getInstance() {
		return sra;
	}

	/**
	 * 找出所有的宾语及其定语
	 * 
	 * @param gi
	 * @param terms
	 */
	public void analyObjects(GrammerItem gi, List<Term> terms) {
		String natures = getNature(terms);
		List<ObjectItem> ois = new ArrayList<ObjectItem>();
		Iterator iterator = SentenceRule.SentenceRuleModel.keySet().iterator();
		while (iterator.hasNext()) {
			String regex = (String) iterator.next();
			if (isRuleFormat(natures, regex)) {
				do {
					SentenceRuleItem ori = SentenceRule.SentenceRuleModel
							.get(regex);
					List<ObjectItem> tmp = getObItems(terms, ori, natures, 0);
					ois.addAll(tmp);
					// 如果有补充子规则，则就进行循环
					if (!"null".equals(ori.getSubRule())) {
						regex = ori.getSubRule();
					} else {
						regex = null;
					}
				} while (regex != null);
				gi.setObjects(ois);
				break;
			}
		}
	}

	/**
	 * 判断是否符合句式
	 * 
	 * @param natures
	 * @param regex
	 * @return
	 */
	private boolean isRuleFormat(String natures, String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(natures).find();
	}

	/**
	 * 获取宾语和定语
	 * 
	 * @param terms
	 * @param ruleItem
	 *            当前的规则
	 * @param tags
	 *            所有的词性组成的字符串
	 * @param indexDiff
	 *            index偏移
	 * @return
	 */
	private List<ObjectItem> getObItems(List<Term> terms,
			SentenceRuleItem ruleItem, String tags, int indexDiff) {
		List<ObjectItem> objectItems = new ArrayList<ObjectItem>();
		Pattern pattern = Pattern.compile(ruleItem.getMainRule());
		Matcher mather = pattern.matcher(tags);
		while (mather.find()) {
			ObjectItem oi = new ObjectItem();
			oi.setConnectIndex(indexDiff + ruleItem.getStartDiff()
					+ mather.start());
			oi.setConnectWord(terms.get(
					indexDiff + ruleItem.getStartDiff() + mather.start())
					.getRealName());
			oi.setIndex(indexDiff + ruleItem.getEndDiff() + mather.end());
			oi.setValue(terms.get(
					indexDiff + ruleItem.getEndDiff() + mather.end())
					.getRealName());
			oi.setAttributive(getAttrs(terms,
					indexDiff + ruleItem.getStartDiff() + mather.start(),
					indexDiff + ruleItem.getEndDiff() + mather.end(), tags));
			objectItems.add(oi);
		}
		return objectItems;
	}

	/**
	 * 获取词性的组合
	 * 
	 * @param terms
	 * @return
	 */
	private String getNature(List<Term> terms) {

		StringBuilder syntaxBuilder = new StringBuilder();
		for (Term term : terms) {
			syntaxBuilder.append(term.getNatureStr().charAt(0));
		}

		return syntaxBuilder.toString();
	}

	/**
	 * 指定开始结束index 和 nature组合 获得定语
	 * 
	 * @param terms
	 * @param startIndex
	 * @param endIndex
	 * @param tags
	 * @return
	 */
	private List<String> getAttrs(List<Term> terms, int startIndex,
			int endIndex, String tags) {
		List<String> res = new ArrayList<String>();
		if ("nz".equals(terms.get(endIndex).getNatureStr())) {
			res.add(terms.get(endIndex).getRealName());
		}
		for (int i = startIndex; i < endIndex; i++) {
			if ('n' == tags.charAt(i) || 'a' == tags.charAt(i)) {
				res.add(terms.get(i).getRealName());
			}
		}
		return res;
	}
}
