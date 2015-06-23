package com.nana.serviceengine.inteceptor;

import java.util.ArrayList;
import java.util.List;

import com.nana.serviceengine.analyzer.ProcessorFactory;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.ruleengine.analyzer.RuleDecisionMaker;
import com.nana.serviceengine.util.MessageSpliter;

public class MessageInteceptor {
	private static MessageInteceptor inteceptor = new MessageInteceptor();

	private MessageInteceptor() {
	}

	public static MessageInteceptor getInstance() {
		return inteceptor;
	}

	/**
	 * 拦截用户消息
	 * 
	 * @param userMessage
	 * @return 认为分配标志
	 */
	public String themeIntecept(UserMessage userMessage) {
		if (userMessage != null && userMessage.getUserid() != null
				&& userMessage.getMessage() != null) {
			// userMessage.setTerms(BaseAnalysis.parse(userMessage.getMessage()));
			if (UserTheme.UserDialog.get(userMessage.getUserid()) != null) {
				UserTheme.UserDialog.get(userMessage.getUserid())
						.getProcessor().setMes(userMessage);
				Thread thread = new Thread(UserTheme.UserDialog.get(
						userMessage.getUserid()).getProcessor());
				thread.start();
				return "servicetask";
			} else {
				ServiceProcessor processor = ProcessorFactory.getInstance()
						.analyze(userMessage);
				if (processor != null) {
					Thread thread = new Thread(processor);
					thread.start();
					return "servicetask";
				} else {
					return "robottask";
				}
			}
		}
		return null;

	}

	/**
	 * 判断是否是历史会话
	 * 
	 * @param userMessage
	 * @return 认为分配标志
	 */
	public String themeIntecept1(UserMessage userMessage) {
		if (userMessage != null && userMessage.getUserid() != null
				&& userMessage.getMessage() != null) {
			// userMessage.setTerms(BaseAnalysis.parse(userMessage.getMessage()));
			if (UserTheme.UserDialog.get(userMessage.getUserid()) != null) {
				UserTheme.UserDialog.get(userMessage.getUserid())
						.getProcessor().setMes(userMessage);
				Thread thread = new Thread(UserTheme.UserDialog.get(
						userMessage.getUserid()).getProcessor());
				thread.start();
				return "servicetask";
			} else {
				try {
					MessageSpliter.getInstance().splitMessage(userMessage);
					// 寻找关键字
					String[] sortedWords = MessagePreTreator.getInstance()
							.getSortedWords(userMessage.getTerms());
					//翻译slots关键字
					String[] domainKeyWords = MessagePreTreator.getInstance().getDomainKeys(sortedWords);
					//如果没有发现领域关键词则放给robot处理
					if(domainKeyWords == null || domainKeyWords.length ==0) return "robottask";
					
					
					// 获得processorClass
					String processorClass = RuleDecisionMaker.getInstance()
							.chooseDomainProcessor(domainKeyWords);
					// 如果获得class名则表明为可以进入领域否则传入robot处理
					ServiceProcessor processor = ProcessorFactory.getInstance()
							.createProcessorByClassName(processorClass,userMessage);
					Thread thread = new Thread(processor);
					thread.start();
					return "servicetask";

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;

	}

}
