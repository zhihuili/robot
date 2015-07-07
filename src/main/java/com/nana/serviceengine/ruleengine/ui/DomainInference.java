package com.nana.serviceengine.ruleengine.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.grammer.bean.GrammerItem;
import com.nana.serviceengine.grammer.bean.ObjectItem;

/**
 * 宾语推理
 * 
 * @author wds
 *
 */
public class DomainInference {
	private static DomainInference di = new DomainInference();

	private DomainInference() {
	}

	public static DomainInference getInstance() {
		return di;
	}

	/**
	 * 这里生成领域的关键词 可以修改变得更灵活
	 * 
	 * @param mes
	 * @return 如果无宾语或者字典中没有相关数据则返回null
	 */
	public List<String[]> getDomains(UserMessage mes) {
		try {
			GrammerItem gi = mes.getGrammerItem();
			List<ObjectItem> oi = gi.getObjects();
			// 如果没有宾语就没有必要在分析下去
			if (oi == null || oi.size() == 0)
				return null;
			List<String[]> res = new ArrayList<String[]>();
			Map<String, String> tmp = new HashMap<String, String>();
			List<String> tmpDomains = new ArrayList<String>();
			for (int i = 0; i < oi.size(); i++) {
				
				String domain = DomainDic.domainKeyWord.get(oi.get(i)
						.getValue()) == null ? null : DomainDic.domainKeyWord
						.get(oi.get(i).getValue()).getDomain();
				// 如果领域值为空继续判断
				if (domain == null)
					continue;

				DomainKeyWord dkWord = DomainDic.domainKeyWord.get(oi.get(i)
						.getConnectWord());
				String connectValue = dkWord == null ? null : dkWord.getValue();
				// 如果不存在当前领域
				if (tmp.get(domain) == null) {
					// 如果连词不为or
					if (!"or".equals(connectValue))
						tmpDomains.add(domain);
					else {
						// 如果为空则将not加入domains
						if (gi.getNotItem() != null)
							tmpDomains.add("not");
						res.add(tmpDomains.toArray(new String[] {}));
						tmpDomains.clear();
					}
				}// 如果不为空就不做处理
			}
			if (tmpDomains.size() != 0) {
				if (gi.getNotItem() != null)
					tmpDomains.add("not");
				res.add(tmpDomains.toArray(new String[] {}));
				tmpDomains.clear();
			}
			return res;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
