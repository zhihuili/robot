
package com.nana.robot.chatterbean.aiml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.nana.robot.chatterbean.Graphmaster;
import com.nana.robot.chatterbean.util.Searcher;

public class AIMLParser {
	/*
	 * Attributes
	 */

	private final Searcher searcher = new Searcher();
	private final AIMLHandler handler = new AIMLHandler();
	private SAXParser parser;

	/*
	 * Constructor
	 */

	public AIMLParser() throws AIMLParserConfigurationException {
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
		} catch (Exception e) {
			throw new AIMLParserConfigurationException(e);
		}
	}

	/*
	 * Methods
	 */

	public void parse(Graphmaster graphmaster, InputStream... sources)
			throws AIMLParserException {
		try {
			for (InputStream aiml : sources)
				parser.parse(aiml, handler);

			graphmaster.append(handler.unload());

		} catch (Exception e) {
			throw new AIMLParserException(e);
		}
	}
}