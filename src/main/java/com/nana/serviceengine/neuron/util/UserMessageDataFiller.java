package com.nana.serviceengine.neuron.util;

import java.util.List;

import org.ansj.splitWord.analysis.ToAnalysis;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.inteceptor.MessagePreTreator;
import com.nana.serviceengine.ruleengine.ui.DomainInference;

public class UserMessageDataFiller {
	private static UserMessageDataFiller userDDF = new UserMessageDataFiller();

	private UserMessageDataFiller() {
	}

	public static UserMessageDataFiller getInstance() {
		return userDDF;
	}
	/**
	 * 分析message并填充结果
	 * @param mes
	 */
	public void dataFill(UserMessage mes) {
		// 分词
		if (mes.getTerms() == null || mes.getTerms().size() == 0)
			mes.setTerms(ToAnalysis.parse(mes.getMessage()));
		// 分析语法
		if (mes.getGrammerItem() == null) {
			mes.setGrammerItem(MessagePreTreator.getInstance().getGrammerItem(
					mes.getTerms()));
		}
		//确定领域词
		if (mes.getDomainKeyWords() == null) {
			mes.setDomainKeyWords(DomainInference.getInstance().getDomains(mes));
		}
	}
	/**
	 * 数据重置
	 * @param mes
	 */
	public void dataReset(UserMessage mes){
		mes.setDomainKeyWords(null);
		mes.setGrammerItem(null);
		mes.setTerms(null);
	}
}
