package com.nana.serviceengine.common.dic;

import java.util.HashMap;
import java.util.Map;

public class HtmlDic {
	public static final Map<String, String> HTMLMAP = new HashMap<String, String>();
	static {
		// TODO init HTMLMAP
		HTMLMAP.put(
				"videohtml",
				"<!DOCTYPE html><html><head><meta http-equiv='content-type' content='text/html;charset=utf-8'>"
						+ "<title>电影列表</title></head><body><table width='100%'><tr><td rowspan='3'><img src='{$imageUrl}' width='45' height='65'/></td><td><font size='1px'>{$name}</font><font color='red' size='2' face='黑体'><B>{$score}</B></font><font size='0.5' color='#A9A9A9'>分</font></td>"
						+ "</tr><tr><td><font size='0.5' color='#A9A9A9'>{$keywordCountList}</font></td></tr><tr><td><font size='0'>{$summary}</font></td></tr></table></body></html>");

	}
}
