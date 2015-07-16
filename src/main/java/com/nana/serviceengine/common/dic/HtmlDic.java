package com.nana.serviceengine.common.dic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.util.TxtReader;

public class HtmlDic {
	public static final Map<String, String> HTMLMAP = new HashMap<String, String>();
	static {
		// TODO init HTMLMAP
		try {
			List<String> lines = new TxtReader("resources/htmldic")
					.getContentLineList();
			for (String str : lines) {
				String[] strs = str.split("\\.\\.");
				HTMLMAP.put(strs[0], strs[1]);
			}

		} catch (Exception ex) {
			// TODO log
			System.out.println("htmldic init failed!");
		}
	}
}
