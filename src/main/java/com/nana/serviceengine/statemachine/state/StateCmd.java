package com.nana.serviceengine.statemachine.state;

import java.util.Map;

import com.nana.serviceengine.common.bean.UserMessage;

public interface StateCmd {
	void doRun(UserMessage mes,Map<String,Object> moreInfo);
}
