package com.nana.serviceengine.grammer.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;

import com.nana.serviceengine.dic.DomainDic;
import com.nana.serviceengine.grammer.bean.GrammerItem;
import com.nana.serviceengine.grammer.bean.ObjectItem;
import com.nana.serviceengine.grammer.bean.PdObjectIndex;

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
		// 先分析获取多个宾语否则获取单个宾语
		if (!getMulteObject(gi, terms))
			getSingleObject(gi, terms);
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
	 * 最新的使用正则匹配的方式来获取定语,并设置ObjectItem 的谓词位置和值
	 * 
	 * @param str
	 * @param terms
	 * @return
	 */
	public List<String> getAttribute(List<Term> terms, ObjectItem oi) {
		String str = getNature(terms);
		PdObjectIndex poi = getPdObjectIndex(str);
		oi.setConnectIndex(poi.getPredicateIndex());
		oi.setConnectWord(terms.get(poi.getPredicateIndex()).getRealName());
		return getAttrs(terms, poi.getPredicateIndex(), poi.getObjectIndex(),
				str);
	}

	public List<String> getAttribute(List<Term> terms, String tags,
			ObjectItem oi) {
		PdObjectIndex poi = getPdObjectIndex(tags);
		oi.setConnectIndex(poi.getPredicateIndex());
		oi.setConnectWord(terms.get(poi.getPredicateIndex()).getRealName());
		return getAttrs(terms, poi.getPredicateIndex(), poi.getObjectIndex(),
				tags);
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

	/**
	 * 获得单宾语
	 * 
	 * @param gi
	 * @param terms
	 * @return
	 */
	private boolean getSingleObject(GrammerItem gi, List<Term> terms) {
		String tags = getNature(terms);
		return getSingleObject(gi, tags, terms);
	}

	private boolean getSingleObject(GrammerItem gi, String tags,
			List<Term> terms) {
		try {
			PdObjectIndex index = getPdObjectIndex(tags);
			// 没有宾语的存在或者宾语没有定语
			if (index == null
					|| (index.getObjectIndex() == 0 && index
							.getPredicateIndex() == 0))
				return false;

			List<String> attributive = getAttrs(terms,
					index.getPredicateIndex(), index.getObjectIndex(), tags);
			ObjectItem oi = new ObjectItem();
			oi.setAttributive(attributive);
			oi.setConnectIndex(index.getPredicateIndex());
			oi.setConnectWord(terms.get(index.getPredicateIndex())
					.getRealName());
			oi.setIndex(index.getObjectIndex());
			oi.setValue(terms.get(index.getObjectIndex()).getRealName());
			gi.getObjects().add(oi);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 获得多的情况宾语
	 * 
	 * @param gi
	 * @param terms
	 */
	private boolean getMulteObject(GrammerItem gi, List<Term> terms) {
		String tags = getNature(terms);
		return getMulteObject(gi, tags, terms);
	}

	private boolean getMulteObject(GrammerItem gi, String tags, List<Term> terms) {
		// 这些正则可以放到外部文件中
		String vncnRegex = "v[a-bd-z]*nc";
		String subVncnRegex = "c[a-bd-z]*n";
		Pattern pattern = Pattern.compile(vncnRegex);
		Matcher mather = pattern.matcher(tags);
		// 默认只匹配一句话所以只判断一次
		if (mather.find()) {
			// 把第一个取出来
			ObjectItem oi = new ObjectItem();
			oi.setIndex(mather.end() - 2);
			oi.setValue(terms.get(mather.end() - 2).getRealName());
			oi.setAttributive(getAttribute(terms,
					tags.substring(0, mather.end() - 1), oi));
			gi.getObjects().add(oi);
			// 把剩下的取出来
			String tmp = tags.substring(mather.end() - 1);
			int index = mather.end() - 1;
			pattern = Pattern.compile(subVncnRegex);
			mather = pattern.matcher(tmp);
			while (mather.find()) {
				ObjectItem sub = new ObjectItem();
				sub.setConnectWord(terms.get(index + mather.start())
						.getRealName());
				sub.setConnectIndex(index + mather.start());
				sub.setIndex(index + mather.start());
				sub.setAttributive(getAttrs(terms, index + mather.start() + 1,
						index + mather.end() - 1, tags));
				sub.setValue(terms.get(index + mather.end() - 1).getRealName());
				gi.getObjects().add(sub);
			}
			return true;
		}
		return false;

	}

	/**
	 * 根据正则来找谓宾 需要去维护正则表达式（句式结构）
	 * 
	 * @param str
	 * @return 谓语和宾语的index
	 */
	private PdObjectIndex getPdObjectIndex(String str) {
		// 这些正则可以保存在文件中
		String[] regexs = new String[] { "v[a-z]*nv", "v[a-z]*n", "[a-uw-z]*n" };
		PdObjectIndex res = null;
		for (int i = 0; i < regexs.length; i++) {
			res = getPdObjectIndex(str, regexs[i]);
			if (res != null) {
				return res;
			}
		}
		return null;
	}

	private PdObjectIndex getPdObjectIndex(String str, String regex) {
		PdObjectIndex res = new PdObjectIndex();
		Pattern pattern = Pattern.compile(regex);
		Matcher mather = pattern.matcher(str);
		if (mather.find()) {
			res.setPredicateIndex(mather.start());
			res.setObjectIndex(mather.end() - 1);
			return res;

		}
		return null;

	}
}
