package com.nana.serviceengine.command.impl;

import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;

import com.nana.common.message.ResponseMessage;
import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.command.StateCmd;
import com.nana.serviceengine.command.StateCommandUtil;
import com.nana.serviceengine.dic.pool.DomainDic;
import com.nana.serviceengine.statemachine.DialogState;

public class DomainReSelectCmd implements StateCmd {
	private static DomainReSelectCmd drs = new DomainReSelectCmd();

	private DomainReSelectCmd() {
	}

	public static DomainReSelectCmd getInstance() {
		return drs;
	}

	@Override
	public void doRun(UserMessage mes, Map<String, Object> moreInfo) {
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		List<Term> terms = (List<Term>) userDialog.getStateInfo().get("terms");
		Integer index = (Integer) userDialog.getStateInfo().get("index");
		String parentValue = (String) userDialog.getStateInfo().get(
				"parentvalue");
		String originMes = (String) userDialog.getStateInfo().get(
				"originmessage");
		List<Term> newTerms = mes.getTerms();
		DomainKeyWord domain = null;
		boolean isQuit = false;
		// 查找用户有没有选择待选的领域
		for (int i = 0; i < newTerms.size(); i++) {
			DomainKeyWord dkw = DomainDic.domainKeyWord.get(newTerms.get(i));
			if (dkw == null)
				continue;
			else {
				if ("not".equals(dkw.getDomain())) {
					isQuit = true;
					break;
				}
				if (parentValue.equals(dkw.getParDomain())) {
					domain = dkw;
					break;
				}
			}
		}
		if (isQuit) {
			// 用户放弃这个问题
			RobotConsumerListener.getInstance().sendMsg("好的。", mes.getReqMessage());
			StateCommandUtil.getInstance().clearState(userDialog, mes);
			return;
		}
		if (domain == null) {
			RobotConsumerListener.getInstance().sendMsg("我听不懂您的回答。",
					mes.getReqMessage());
			RobotConsumerListener.getInstance().sendMsg(
					userDialog.getResMessages().get(
							userDialog.getResMessages().size() - 1),
					mes.getReqMessage());
			userDialog.setPreDialogState(DialogState.RESELECT);
			return ;
			// StateCommandUtil.getInstance().goPreQuitState(mes, userDialog,
			// rpMessage);
		}
		userDialog.getStateInfo().clear();
		// 替换消息实体后继续执行
		StateCommandUtil.getInstance().goRecommand(userDialog,
				domain.getKeyWord(), index, terms, mes);

	}

}
