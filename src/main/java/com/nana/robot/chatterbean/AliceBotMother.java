package com.nana.robot.chatterbean;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.nana.robot.chatterbean.parser.AliceBotParser;
import com.nana.robot.chatterbean.parser.AliceBotParserConfigurationException;
import com.nana.robot.chatterbean.parser.AliceBotParserException;
import com.nana.robot.chatterbean.util.Searcher;
import com.nana.robot.customexception.AppException;

public class AliceBotMother {
	/*
	 * Attribute Section
	 */

	private ByteArrayOutputStream gossip = null;

	/*
	 * Event Section
	 */

	public void setUp() {
		// gossip = new ByteArrayOutputStream();
	}

	/*
	 * Method Section
	 */

	public String gossip() {
		return gossip.toString();
	}

	public AliceBot newInstance() {
		Searcher searcher = new Searcher();
		AliceBotParser parser;
		AliceBot bot = null;
		try {
			parser = new AliceBotParser();
			bot = parser.parse(new FileInputStream("./Corpus/context.xml"),
					new FileInputStream("./Corpus/splitters.xml"),
					new FileInputStream("./Corpus/substitutions.xml"),
					searcher.search("./Corpus/Chinese", ".*\\.xml"));
		} catch (AliceBotParserConfigurationException e) {
			throw new AppException(e);
		} catch (FileNotFoundException e) {
			throw new AppException("[ExceptionInfo]相关文件没有找到。", e);
		} catch (AliceBotParserException e) {
			throw new AppException(e);
		} catch (IOException e) {
			throw new AppException(e);
		}

		// Context context = bot.getContext();
		// context.outputStream(gossip);
		return bot;
	}
}
