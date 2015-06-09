package com.nana.robot.api.context;

import com.nana.robot.api.aiml.AskToAIML;

public class ChartManager {
	private static final AskToAIML askToAIML = new AskToAIML();
	private static ChartContext chartContext;
	private static ChartManager instance;

	private ChartManager() {

		chartContext = new ChartContext(askToAIML);
	}

	public static synchronized ChartManager getInstance() {
		if (instance == null) {
			instance = new ChartManager();
		}
		return instance;
	}

	public String response(String input) {
		return chartContext.response(input);
	}
}
