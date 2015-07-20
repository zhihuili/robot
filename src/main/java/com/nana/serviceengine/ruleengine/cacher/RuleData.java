package com.nana.serviceengine.ruleengine.cacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.util.TxtReader;
import com.nana.serviceengine.ruleengine.bean.Rule;
import com.nana.serviceengine.ruleengine.bean.RuleReverseItem;

public class RuleData {
	public static Map<String, Rule> Rules = new HashMap<String, Rule>();
	public static Map<String, RuleReverseItem> ReverseTable = new HashMap<String, RuleReverseItem>();
	
    static{   	
		try {
			List<String> lines = new TxtReader("resources/domainrule")
					.getContentLineList();
			for (int i = 0; i < lines.size(); i++) {
				String[] tmp = lines.get(i).split(" ");
				Rule rule = new Rule();
				rule.setId(tmp[0]);
				rule.setData(tmp[1]);
				rule.setDomainClass(tmp[2]);
				Rules.put(tmp[0], rule);
			}
			List<String> relines = new TxtReader("resources/ruleReverse")
					.getContentLineList();
			for (int i = 0; i < relines.size(); i++) {
				String[] tmp = relines.get(i).split(" ");
				RuleReverseItem rri = new RuleReverseItem();
				rri.setWordType(tmp[0]);
				rri.setAuthValue(Integer.parseInt(tmp[1]));
				rri.setIndexs(tmp[2]);
				ReverseTable.put(tmp[0], rri);
			}
		} catch (Exception ex) {
			//TODO Log
			ex.printStackTrace();
		}
    }
	
	


	

}
