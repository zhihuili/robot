package com.nana.serviceengine.neuron.util;

import java.util.ArrayList;
import java.util.List;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.neuron.processor.factory.ProcessorFactory;
import com.nana.serviceengine.ruleengine.ui.RuleDecisionMaker;

/**
 * 主要用来分析processors
 * 
 * @author wds
 *
 */
public class UserDialogDataFiller {
	private static UserDialogDataFiller udc = new UserDialogDataFiller();

	private UserDialogDataFiller() {
	}

	public static UserDialogDataFiller getInstance() {
		return udc;
	}

	/**
	 * 返回新的已得到的processors
	 * 
	 * @param userDialog
	 * @param mes
	 * @return
	 */
	public List<ServiceProcessor> dialogProcessorsFill(UserMessage mes) {

		List<String[]> domainKeyWords = mes.getDomainKeyWords();
		if (domainKeyWords == null || domainKeyWords.size() == 0)
			return null;
		List<ServiceProcessor> processors = new ArrayList<ServiceProcessor>();
		for (int i = 0; i < domainKeyWords.size(); i++) {
			try {
				// 获得processorClass
				String processorClass = RuleDecisionMaker.getInstance()
						.chooseDomainProcessor(domainKeyWords.get(i));

				// 如果获得class名则表明为可以进入领域否则传入robot处理
				if (processorClass != null) {
					ServiceProcessor processor = ProcessorFactory.getInstance()
							.createProcessorByClassName(processorClass, mes);
					processors.add(processor);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return processors;
	}

}
