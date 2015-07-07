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
import com.nana.serviceengine.statemachine.bean.DialogState;
import com.nana.serviceengine.statemachine.state.StateCmd;
import com.nana.serviceengine.statemachine.util.StateCommandUtil;

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
			RobotResponser.getInstance().responseMessage("好的。", mes);
			StateCommandUtil.getInstance().clearState(userDialog, mes);
			return;
		}
		if (domain == null) {
			RobotResponser.getInstance().responseMessage("我听不懂您的回答。", mes);
			RobotResponser.getInstance().responseMessage(userDialog.getResMessages().get(userDialog.getResMessages().size() - 1), mes);
			userDialog.setPreDialogState(DialogState.RESELECT);
			return;
		}
		userDialog.getStateInfo().clear();
		// 替换消息实体后继续执行
		StateCommandUtil.getInstance().goRecommand(userDialog,
				domain.getKeyWord(), index, terms, mes);

	}

}
