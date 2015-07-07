package com.nana.serviceengine.statemachine.state.impl;

import java.util.List;
import java.util.Map;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.neuron.domainparam.DomainParam;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.neuron.util.UserDialogDataFiller;
import com.nana.serviceengine.neuron.util.UserMessageDataFiller;
import com.nana.serviceengine.statemachine.bean.LoadType;
import com.nana.serviceengine.statemachine.state.StateCmd;

/**
 * 在这边进行领域是否切换的判断，如果切换的话 对原有的对话进行清理（也可保存到数据库）
 * 
 * @author wds
 *
 */
public class FinishedCmd implements StateCmd {

	private static FinishedCmd fc = new FinishedCmd();

	private FinishedCmd() {
	}

	public static FinishedCmd getInstance() {
		return fc;
	}

	@Override
	public void doRun(UserMessage mes, Map<String, Object> moreInfo) {
		// 先对message进行处理
		UserMessageDataFiller.getInstance().dataFill(mes);
		// 首先把会话数据全部弄出来
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());

		List<ServiceProcessor> processors = UserDialogDataFiller.getInstance()
				.dialogProcessorsFill(mes);

		if (processors == null
				|| processors.size() == 0
				|| (userDialog.getProcessors() != null && processors.size() == 1 && processors
						.get(0)
						.getClass()
						.equals(userDialog.getProcessors()
								.get(userDialog.getProcessors().size() - 1)
								.getClass()))) {
			// 尝试进行上一领域的属性获取
			DomainParam param = (DomainParam) userDialog.getParam();
			param.dataCollect(mes);
			if (param.getLoadType() != null
					&& !param.getLoadType().equals(LoadType.NOLOAD)) {
				//如果正确收集到了信息则旧的processor在次执行
				Thread thread = new Thread(userDialog.getProcessors().get(
						userDialog.getProcessors().size() - 1));
				thread.start();
				return;
			} 
		}
		// 如果确定转换领域
		userDialog.setProcessors(processors);
		userDialog.setParam(null);
		StartCmd.getInstance().doRun(mes, null);

	}
}
