package com.nana.serviceengine.statemachine;

import java.util.List;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;
import com.nana.serviceengine.statemachine.bean.DialogState;

public class TaskDispather {
	private static TaskDispather td = new TaskDispather();

	private TaskDispather() {
	}

	public static TaskDispather getInstance() {
		return td;
	}

	public void DoNextAction(UserDialog userDialog) {
		List<ServiceProcessor> processors = userDialog.getProcessors();
		if (processors.size() == 0) {
			userDialog.setState(DialogState.START);

		} else {
			ServiceProcessor processor = userDialog.getProcessors().get(
					userDialog.getProcessors().size() - 1);
			Thread thread = new Thread(processors.get(processors.size() - 1));
			thread.start();
		}
	}
}
