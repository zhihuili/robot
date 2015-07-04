package com.nana.serviceengine.command.impl;

import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;

import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.command.StateCmd;
import com.nana.serviceengine.command.StateCommandUtil;
import com.nana.serviceengine.dic.pool.DomainDic;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.util.UserMessageDataFiller;

public class WaitStateCmd implements StateCmd {

	private static WaitStateCmd wsc = new WaitStateCmd();

	private WaitStateCmd() {
	}

	public static WaitStateCmd getInstance() {
		return wsc;
	}

	@Override
	public void doRun(UserMessage mes, Map<String, Object> moreInfo) {
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		UserMessageDataFiller.getInstance().dataFill(mes);
		List<Term> terms = mes.getTerms();
		boolean hasNo = false;
		for (int i = 0; i < terms.size(); i++) {
			if ("not".equals(DomainDic.domainKeyWord.get(
					terms.get(i).getRealName()).getDomain())) {
				hasNo = true;
			}
		}
		if (hasNo) {
			//如果用户选择了放弃
			RobotConsumerListener.getInstance().sendMsg("好的。", mes.getReqMessage());
			StateCommandUtil.getInstance().clearState(userDialog, mes);
		} else {
			//
			List<ServiceProcessor> processors = userDialog.getProcessors();
			ServiceProcessor processor = userDialog.getProcessors().get(
					userDialog.getProcessors().size() - 1);
			// 把当前消息实体放进processor
			processor.setMes(mes);
			Thread thread = new Thread(processor);
			thread.start();
		}
	}

}
