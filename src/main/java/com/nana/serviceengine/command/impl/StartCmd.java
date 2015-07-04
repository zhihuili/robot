package com.nana.serviceengine.command.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nana.robot.api.context.ChartManager;
import com.nana.serviceengine.analyzer.ProcessorFactory;
import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.command.StateCmd;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.ruleengine.analyzer.RuleDecisionMaker;
import com.nana.serviceengine.util.UserDialogDataFiller;
import com.nana.serviceengine.util.UserMessageDataFiller;

public class StartCmd implements StateCmd {

	private static StartCmd sc = new StartCmd();

	private StartCmd() {
	}

	public static StartCmd getInstance() {
		return sc;
	}

	@Override
	public void doRun(UserMessage mes, Map<String, Object> moreInfo) {
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		UserMessageDataFiller.getInstance().dataFill(mes);
		//如果没有处理器则分析新的消息获取处理器
		if (userDialog.getProcessors() == null
				|| userDialog.getProcessors().size() == 0) {
			List<ServiceProcessor> processors = UserDialogDataFiller
					.getInstance().dialogProcessorsFill(mes);
			userDialog.setProcessors(processors);
		}
		if (userDialog.getProcessors() == null
				|| userDialog.getProcessors().size() == 0) {
			// 进到聊天机器人
			ChartManager.getInstance().response(mes);
		
		} else {
			Thread thread = new Thread(userDialog.getProcessors().get(userDialog.getProcessors().size()-1));
			thread.start();
		}

	}

}
