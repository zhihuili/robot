package com.nana.serviceengine.neuron.responsecreator;

import com.nana.common.message.ResponseMessage;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.neuron.domainparam.DomainParam;

public interface SentenceCreator {
	ResponseMessageAdapter createSentence(DomainParam params);
}
