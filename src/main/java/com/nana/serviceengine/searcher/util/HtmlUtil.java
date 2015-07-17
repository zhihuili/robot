package com.nana.serviceengine.searcher.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlUtil {
	private static HtmlUtil hu = new HtmlUtil();

	private HtmlUtil() {
	}

	public static HtmlUtil getInstance() {
		return hu;
	}
	/**
	 * 删除html标签
	 * @param source
	 * @return
	 */
	public String deleteHtmlTags(String source) {
		try {
			Pattern tagPattern = Pattern.compile("<[a-z]+ [^>]*>");
			Matcher matcher = tagPattern.matcher(source);
			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "");
			}
			matcher.appendTail(buffer);
			return buffer.toString();
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * 去除停用词
	 * 
	 * @param source
	 * @param stopWords
	 * @return
	 */
	public String deleteStopWords(String source, String stopWords) {
		try {
			StringBuilder builder = new StringBuilder();
			char[] tem = source.toCharArray();
			for (int i = 0; i < tem.length; i++) {
				if (stopWords.indexOf(tem[i]) < 0)
					builder.append(tem[i]);
			}
			return builder.toString().trim();
		} catch (Exception ex) {
			return null;
		}
	}
}
