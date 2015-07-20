package com.nana.serviceengine.inout.responsecenter;

import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.adapter.ResponseMessageAdapter;
import com.nana.serviceengine.common.bean.UserMessage;

/**
 * 所有的消息已回调函数的方式从这里出去，如果回调函数发生变化需要修改此类
 * @author wds
 *
 */
public class RobotResponser {
	private static RobotResponser rr = new RobotResponser();
	private RobotResponser(){}
	
	public static RobotResponser getInstance(){
		return rr;
	}
	/**
	 * 返回String消息
	 * @param mes
	 * @param message
	 */
	public void responseMessage(String mes,UserMessage message){
		RobotConsumerListener.getInstance().sendMsg(mes, message.getReqMessage());
	}
	
	/**
	 * 返回ResponseMessage 消息
	 * @param rpMessage
	 * @param message
	 */
	public void responseMessage(ResponseMessageAdapter rpMessage,UserMessage message){
		RobotConsumerListener.getInstance().sendMsg(rpMessage.getRpMessage(), message.getReqMessage());
	}
	
	/**
	 * 指定audio消息和display消息 以后如果参数增多，可能需要在程序中构造完，调用responseMessage(ResponseMessageAdapter rpMessage,UserMessage message) 发送消息
	 * @param audioTxt
	 * @param disPlayTxt
	 * @param message
	 */
	public void responseMessage(String audioTxt,String disPlayTxt,UserMessage message){
		ResponseMessageAdapter rpMessage = new ResponseMessageAdapter();
		rpMessage.setAudioText(audioTxt);
		rpMessage.setDisplayText(disPlayTxt);
		rpMessage.setUserId(message.getUserid());
		responseMessage(rpMessage, message);
	}
	
}
