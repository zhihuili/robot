package com.nana.serviceengine.analyzer;

import java.util.List;

import com.nana.robot.api.context.ChartManager;
import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.command.StateCmd;
import com.nana.serviceengine.command.impl.DomainClearCmd;
import com.nana.serviceengine.command.impl.DomainReSelectCmd;
import com.nana.serviceengine.command.impl.FinishedCmd;
import com.nana.serviceengine.command.impl.PreQuitCmd;
import com.nana.serviceengine.command.impl.StartCmd;
import com.nana.serviceengine.command.impl.WaitStateCmd;
import com.nana.serviceengine.dic.pool.DomainDic;
import com.nana.serviceengine.ruleengine.analyzer.DomainInference;
import com.nana.serviceengine.statemachine.DialogState;
import com.nana.serviceengine.util.UserMessageDataFiller;

public class ActionSelector {
	private static ActionSelector actionSelector = new ActionSelector();

	private ActionSelector() {
	}

	public static ActionSelector getInstance() {
		return actionSelector;
	}

	public StateCmd getCommand(UserMessage mes) {

		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		if (userDialog == null) {
			userDialog = new UserDialog();
			userDialog.setState(DialogState.START);
			UserTheme.UserDialog.put(mes.getUserid(), userDialog);
		}
		if (userDialog.getState().equals(DialogState.START)) {
			UserMessageDataFiller.getInstance().dataFill(mes);
			List<String[]> domainKeyWords = DomainInference.getInstance()
					.getDomains(mes);

			// userDialog.getStateInfo().put("domainKeyWords",domainKeyWords);保存在UserMessage中了
			// 不需要保存了
			if (domainKeyWords != null)
				for (int j = 0; j < domainKeyWords.size(); j++) {
					String[] domains = domainKeyWords.get(j);
					for (int i = 0; i < domains.length; i++) {
						DomainKeyWord dkw = DomainDic.domainKeyWord
								.get(domains[i]);
						if (dkw != null && "parent".equals(dkw.getDomain())) {
							mes.setDomainKeyWords(domainKeyWords);
							return DomainClearCmd.getInstance();
						}
					}
				}
			return StartCmd.getInstance();
		}
		if (userDialog.getState().equals(DialogState.RESELECT)) {
			return DomainReSelectCmd.getInstance();
		}

		if (userDialog.getState().equals(DialogState.PREQUIT)) {
			return PreQuitCmd.getInstance();
		}
		if (userDialog.getState().equals(DialogState.WAIT)) {
			return WaitStateCmd.getInstance();
		}
		if (userDialog.getState().equals(DialogState.FINISHED)) {
			return FinishedCmd.getInstance();
		}

		return null;
	}

}
