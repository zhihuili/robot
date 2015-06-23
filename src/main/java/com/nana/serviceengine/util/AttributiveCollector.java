package com.nana.serviceengine.util;

import java.util.List;

import com.nana.serviceengine.bean.UserMessage;

public class AttributiveCollector {
	private static AttributiveCollector ac = new AttributiveCollector();

	private AttributiveCollector() {
	}

	public static AttributiveCollector getInstance() {
		return ac;
	}

	public String getAttributive(UserMessage mes, String noun) {
		StringBuilder builder = new StringBuilder();
		List<String> assistantAttr = getAssistantAttributive();
		for (String str : assistantAttr) {
			if (mes.getMessage().contains(str)) {
				builder.append(str + " ");
			}
		}
		int index;
		for (index = 0; index < mes.getTerms().size(); index++) {
			if(noun.equals(mes.getTerms().get(index).getRealName())){
				break;
			}
		}
		
		if(!"n".equals(mes.getTerms().get(--index).getNatureStr().charAt(0))){
			
		}

		return null;
	}

	public List<String> getAssistantAttributive() {
		try {
			return new TxtReader("resources/attributive").getContentLineList();

		} catch (Exception ex) {
			// TODO log
			ex.printStackTrace();
		}
		return null;
	}
}
