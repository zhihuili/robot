package com.nana.serviceengine.searcher.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nana.serviceengine.searcher.bean.TagItem;

/**
 * 此类用来收集html中的内容类似<div>contttttt<div>标签div中的内容，一定注意自己收集的标签中内部不能有相同的标签
 * 
 * @author wds
 *
 */
public class ItemTagCollector {
	private static ItemTagCollector ic = new ItemTagCollector();

	private ItemTagCollector() {
	}

	public static ItemTagCollector getInstance() {
		return ic;
	}
	/**
	 * 获取所有的符合模版正则的标签包含的信息
	 * @param orginHtml
	 * @param tagItem
	 * @return 标签内信息的集合
	 */
	public List<String> getItems(String orginHtml, TagItem tagItem) {
		List<String> res = new ArrayList<String>();
		List<Integer> startIndex = new ArrayList<Integer>();
		List<Integer> endIndex = new ArrayList<Integer>();
		//将所有符合taghtml的标签都找出来，并把所在index放到startIndex集合
		Pattern startPattern = Pattern.compile("<" + tagItem.getTag() + " [^>]*>");
		Matcher startMatcher = startPattern.matcher(orginHtml);
		while (startMatcher.find()) {
			if(startMatcher.group().contains("/>")) continue;
			startIndex.add(startMatcher.start());
		}
		//将所有符合taghtml的标签的结束标签都找出来，并把所在index放到endIndex集合
		Pattern endPattern = Pattern.compile("</" + tagItem.getTag() + ">");
		Matcher endMatcher = endPattern.matcher(orginHtml);
		while (endMatcher.find()) {
			endIndex.add(endMatcher.start());
		}

		Pattern pattern = Pattern.compile(tagItem.getTagHead());
		Matcher matcher = pattern.matcher(orginHtml);
		int sindex = 0;
		int eindex = 0;
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();

			for (int i = 0; i < startIndex.size(); i++) {
				if (start == startIndex.get(i)) {
					sindex = i;
					eindex = i;
					break;
				}
			}
			if (sindex < startIndex.size() - 1) {
				for (int i = sindex + 1; i < startIndex.size(); i++) {
					if (startIndex.get(i) < endIndex.get(eindex)) {
						eindex++;
					} else {
						res.add(orginHtml.substring(end, endIndex.get(eindex)));
					}
				}
			}else{	
				res.add(orginHtml.substring(end, endIndex.get(sindex)));
			}
		}
		return res;
	}

}
