
package com.nana.robot.chatterbean.aiml;

import org.xml.sax.Attributes;

import com.nana.robot.chatterbean.Match;

public class Bot extends TemplateElement {
	/*
	 * Attributes
	 */

	private String name;

	/*
	 * Constructors
	 */

	public Bot(Attributes attributes) {
		name = attributes.getValue(0);
	}

	public Bot(String name) {
		this.name = name;
	}

	/*
	 * Methods
	 */

	public boolean equals(Object obj) {
		return (super.equals(obj) && name.equals(((Bot) obj).name));// 这里super这样调用equals第一次见啊？
	}

	public int hashCode() {
		return name.hashCode();
	}

	public String process(Match match) {
		try {
			String value = (String) match.getCallback().getContext()
					.property("bot." + name);
			return (value != null ? value : "");
		} catch (NullPointerException e) {// 这里是作者主动抓的异常。为什么要这样做呢？
			return "";
		}
	}
}
