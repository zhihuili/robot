package com.nana.serviceengine.sentence;

import com.nana.common.message.ResponseMessage;
import com.nana.serviceengine.dao.bean.DomainParam;

public interface SentenceCreator {
	ResponseMessage createSentence(DomainParam params);
}
