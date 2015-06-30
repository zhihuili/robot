package com.nana.serviceengine.command;

import java.util.Map;

import com.nana.serviceengine.bean.UserMessage;

public interface StateCmd {
	void doRun(UserMessage mes,Map<String,Object> moreInfo);
}
