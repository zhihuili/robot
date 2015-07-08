package com.nana.serviceengine.statemachine.state.impl;

import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;

import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.cacher.UserTheme;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.inout.responsecenter.RobotResponser;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.neuron.util.UserMessageDataFiller;
import com.nana.serviceengine.statemachine.state.StateCmd;
import com.nana.serviceengine.statemachine.util.StateCommandUtil;

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
			DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms.get(i).getRealName());
			if (dkw != null && "not".equals(dkw.getDomain())) {
				hasNo = true;
			}
		}
		if (hasNo) {
			//如果用户选择了放弃
			RobotResponser.getInstance().responseMessage("好的。", mes);
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
