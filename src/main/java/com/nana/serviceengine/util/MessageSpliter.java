package com.nana.serviceengine.util;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.ansj.serviceprovider.SplitService;
import com.nana.serviceengine.bean.UserMessage;

public class MessageSpliter {
	private static MessageSpliter ms = new MessageSpliter();

	private MessageSpliter() {
	}

	public static MessageSpliter getInstance() {
		return ms;
	}

	public void splitMessage(UserMessage message) {
		try {
			List<Term> res = SplitService.getInstance().splitSentence(
					message.getMessage());
			message.setTerms(res);
		} catch (Exception ex) {
			// TODO LOG
		}
	}
}
