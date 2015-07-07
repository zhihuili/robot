package com.nana.serviceengine.ruleengine.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.ruleengine.bean.Rule;
import com.nana.serviceengine.ruleengine.bean.RuleReverseItem;
import com.nana.serviceengine.ruleengine.cacher.RuleData;
import com.nana.serviceengine.ruleengine.util.StringArrayList;

public class RuleDecisionMaker {

	private static RuleDecisionMaker rdm = new RuleDecisionMaker();

	private RuleDecisionMaker() {
	}

	public static RuleDecisionMaker getInstance() {
		return rdm;
	}

	/**
	 * 选择规则
	 * 
	 * @param keyWords
	 * @return
	 */
	public String chooseDomainProcessor(String[] keyWords) {
		try {
			List<String> keyWordList = Arrays.asList(keyWords);
			int startAvilableIndex = 0;
			RuleReverseItem rrs = null;
			while (rrs == null && startAvilableIndex < keyWords.length) {
				try {
					rrs = RuleData.ReverseTable.get(keyWords[startAvilableIndex]);
					if (rrs == null)
						startAvilableIndex++;
				} catch (Exception ex) {
					startAvilableIndex++;
				}
			}
			if (rrs == null)
				return null;
			// 第一次最大的集合
			List<String> res = StringArrayList
					.parseString(rrs.getIndexs(), ",");
			// 纪录所有的rule的id
			Map<String, Double> indexs = new HashMap<String, Double>();
			List<String> res1 = null;
			List<Rule> rules = new ArrayList<Rule>();
			// 每次与操作
			for (int i = startAvilableIndex + 1; i < keyWords.length; i++) {
				RuleReverseItem rrItem = RuleData.ReverseTable.get(keyWords[i]);
				if (rrItem == null)
					continue;
				res1 = StringArrayList.parseString(rrItem.getIndexs(), ",");

				if (hasCommon(res, res1))
					res.retainAll(res1);
			}
			for (int i = 0; i < res.size(); i++) {
				Rule rule = RuleData.Rules.get(res.get(i));
				List<String> ruleList = StringArrayList.parseString(
						rule.getData(), ",");

				int sum = 0;
				// 被除数
				double sp = 1d;
				for (int j = 0; j < ruleList.size(); j++) {
					if (keyWordList.contains(ruleList.get(j)))
						sum += RuleData.ReverseTable.get(ruleList.get(j))
								.getAuthValue();
				}
				if (ruleList.size() > 1)
					sp = ruleList.size() / 2d;
				indexs.put(rule.getId(), sum / sp);
			}
			String maxIndex = null;
			double maxAuth = 0;
			for (Map.Entry<String, Double> entry : indexs.entrySet()) {
				if (maxAuth < entry.getValue()) {
					maxIndex = entry.getKey();
					maxAuth = entry.getValue();
				}
			}
			return RuleData.Rules.get(maxIndex).getDomainClass();

		} catch (Exception ex) {
			// TODO LOG
			ex.printStackTrace();
		}
		return null;
	}

	public boolean hasCommon(List<String> tmp1, List<String> tmp2) {
		try {
			for (String str : tmp1) {
				if (tmp2.contains(str))
					return true;
			}
		} catch (Exception ex) {

		}
		return false;
	}
}
