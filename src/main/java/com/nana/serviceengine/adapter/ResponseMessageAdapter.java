package com.nana.serviceengine.adapter;

import com.nana.common.message.ResponseMessage;

public class ResponseMessageAdapter {
	
	private ResponseMessage rpMessage = new ResponseMessage();
	
	public ResponseMessage getRpMessage() {
		return rpMessage;
	}
	public void setRpMessage(ResponseMessage rpMessage) {
		this.rpMessage = rpMessage;
	}
	public String getUserId() {
		return rpMessage.getId();
	}
	public void setUserId(String userId) {
		rpMessage.setId(userId);
	}
	public String getAudioText() {
		return rpMessage.getAudioText();
	}
	public void setAudioText(String audioText) {
		rpMessage.setAudioText(audioText);
	}
	public String getDisplayText() {
		return rpMessage.getDisplayText();
	}
	public void setDisplayText(String displayText) {
		rpMessage.setDisplayText(displayText);
	}
	
	
	
	
}
