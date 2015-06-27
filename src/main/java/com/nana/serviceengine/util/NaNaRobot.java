package com.nana.serviceengine.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ansj.splitWord.analysis.ToAnalysis;

import com.nana.robot.api.context.ChartManager;
import com.nana.robot.chatterbean.util.Translate;
import com.nana.serviceengine.analyzer.ProcessorFactory;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.inteceptor.MessagePreTreator;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.ruleengine.analyzer.DomainInference;
import com.nana.serviceengine.ruleengine.analyzer.RuleDecisionMaker;

public class NaNaRobot {
	private static NaNaRobot robot = new NaNaRobot();

	private NaNaRobot() {
	}

	public static NaNaRobot getInstance() {
		return robot;
	}

	public void getAnswer(UserMessage userMessage) {
		if (userMessage != null && userMessage.getUserid() != null
				&& userMessage.getMessage() != null) {
			try {
				// 分词
				userMessage
						.setTerms(ToAnalysis.parse(userMessage.getMessage()));
				// 分析语法
				userMessage.setGrammerItem(MessagePreTreator.getInstance()
						.getGrammerItem(userMessage.getTerms()));

				// 寻找关键字
				// String[] sortedWords =
				// MessagePreTreator.getInstance().getSortedWords(userMessage.getTerms());

				// 翻译slots关键字
				// String[] domainKeyWords =
				// MessagePreTreator.getInstance().getDomainKeys(sortedWords);

				// 寻找领域关键字
				List<String[]> domainKeyWords = DomainInference.getInstance()
						.getDomains(userMessage);
				List<ServiceProcessor> processors = new ArrayList<ServiceProcessor>();
				if (domainKeyWords != null && domainKeyWords.size() != 0) {
					
					for (int i = 0; i < domainKeyWords.size(); i++) {
						try {
							// 获得processorClass
							String processorClass = RuleDecisionMaker
									.getInstance().chooseDomainProcessor(
											domainKeyWords.get(i));

							// 如果获得class名则表明为可以进入领域否则传入robot处理
							ServiceProcessor processor = ProcessorFactory
									.getInstance().createProcessorByClassName(
											processorClass, userMessage);
							processors.add(processor);
						} catch (Exception ex) {

						}
					}
				}
				// 如果对话中已有主题
				if (UserTheme.UserDialog.get(userMessage.getUserid()) != null) {
					UserDialog dialog = UserTheme.UserDialog.get(userMessage
							.getUserid());
					if (processors.size() == 0
							|| (processors.size() == 1
									&& processors.get(0).getClass().getName() == dialog
											.getProcessors().get(0).getClass()
											.getName() && dialog
									.getProcessors().size() == 1)) {
						// 属性修改
						ServiceProcessor processor = UserTheme.UserDialog
								.get(userMessage.getUserid()).getProcessors()
								.get(0);
						processor.setMes(userMessage);
						Thread thread = new Thread(processor);
						thread.start();
						return;
					} else {
						// 主题切换,删除历史会话
						UserTheme.UserDialog.remove(userMessage.getUserid());

					}
				}
				// 如果没有发现领域关键词则放给robot处理
				if (processors.size() == 0) {
					doRobotTask(userMessage);
				} else {
					UserDialog userDialog = new UserDialog();
					userDialog.setCount(1);
					userDialog.setLastDialog(new Date());
					userDialog.setUserid(userMessage.getUserid());
					userDialog.setProcessors(processors);
					UserTheme.UserDialog.put(userMessage.getUserid(),
							userDialog);
					Thread thread = new Thread(processors.get(0));
					thread.start();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				doRobotTask(userMessage);
			}

		}
	}

	public void doRobotTask(UserMessage mes) {
		String output = Translate.translateString(mes.getMessage());
		ChartManager.getInstance().response(mes);
	}
}
