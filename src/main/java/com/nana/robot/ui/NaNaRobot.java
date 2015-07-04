package com.nana.robot.ui;

import com.nana.robot.api.context.ChartManager;
import com.nana.robot.chatterbean.util.Translate;
import com.nana.serviceengine.analyzer.ActionSelector;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.command.StateCmd;

public class NaNaRobot {
	private static NaNaRobot robot = new NaNaRobot();

	private NaNaRobot() {
	}

	public static NaNaRobot getInstance() {
		return robot;
	}

	public void getAnswer(UserMessage userMessage) {
		try {

			StateCmd command = ActionSelector.getInstance().getCommand(
					userMessage);
			command.doRun(userMessage, null);

			// // 寻找领域关键字
			// List<String[]> domainKeyWords = DomainInference.getInstance()
			// .getDomains(userMessage);
			//
			// // 如果对话中已有主题
			// if (UserTheme.UserDialog.get(userMessage.getUserid()) != null) {
			// UserDialog dialog = UserTheme.UserDialog.get(userMessage
			// .getUserid());
			// if (processors.size() == 0
			// || (processors.size() == 1
			// && processors.get(0).getClass().getName() == dialog
			// .getProcessors().get(0).getClass()
			// .getName() && dialog
			// .getProcessors().size() == 1)) {
			// // 属性修改
			// ServiceProcessor processor = UserTheme.UserDialog
			// .get(userMessage.getUserid()).getProcessors()
			// .get(0);
			// processor.setMes(userMessage);
			// Thread thread = new Thread(processor);
			// thread.start();
			// return;
			// } else {
			// // 主题切换,删除历史会话
			// UserTheme.UserDialog.remove(userMessage.getUserid());
			//
			// }
			// }
			// // 如果没有发现领域关键词则放给robot处理
			// if (processors.size() == 0) {
			// doRobotTask(userMessage);
			// } else {
			// UserDialog userDialog = new UserDialog();
			// userDialog.setCount(1);
			// userDialog.setLastDialog(new Date());
			// userDialog.setUserid(userMessage.getUserid());
			// userDialog.setProcessors(processors);
			// UserTheme.UserDialog.put(userMessage.getUserid(),
			// userDialog);
			// Thread thread = new Thread(processors.get(0));
			// thread.start();
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
			ChartManager.getInstance().response(userMessage);
		}

	}
}
