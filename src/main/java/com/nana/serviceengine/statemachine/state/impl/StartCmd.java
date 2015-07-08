package com.nana.serviceengine.statemachine.state.impl;

import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.neuron.util.UserDialogDataFiller;
import com.nana.serviceengine.neuron.util.UserMessageDataFiller;
import com.nana.serviceengine.outin.robot.ChatRobotCenter;
import com.nana.serviceengine.statemachine.state.StateCmd;

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
			// 聊天机器人工作
			RobotResponser.getInstance().responseMessage(ChatRobotCenter.getInstance().getRobotResponse(mes), mes);		
		} else {
			//String[] domainKeyWords = mes.getDomainKeyWords().get(userDialog.getProcessors().size()-1);
			//userDialog.setCurrentDomain(domainKeyWords[domainKeyWords.length-1]);
			Thread thread = new Thread(userDialog.getProcessors().get(userDialog.getProcessors().size()-1));
			thread.start();
		}

	}

}
