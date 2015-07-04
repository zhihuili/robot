package com.nana.serviceengine.command;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.common.message.ResponseMessage;
import com.nana.robot.ui.RobotConsumerListener;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.command.impl.StartCmd;
import com.nana.serviceengine.statemachine.DialogState;
import com.nana.serviceengine.util.UserMessageDataFiller;

public class StateCommandUtil {
	private static StateCommandUtil scu = new StateCommandUtil();

	private StateCommandUtil() {
	}

	public static StateCommandUtil getInstance() {
		return scu;
	}

	/**
	 * 清除参数状态
	 * @param userDialog
	 * @param mes
	 */
	public void clearState(UserDialog userDialog,UserMessage mes){
		userDialog.getProcessors().clear();
		userDialog.getResMessages().clear();
		userDialog.getStateInfo().clear();
		sendMsg("好的。",mes);
		userDialog.setState(DialogState.START);
		
	}
	
	
	
	// 替换宾语再次分析
	public void goRecommand(UserDialog userDialog, String aimDomain, int index,
			UserMessage mes) {
		goRecommand(userDialog,aimDomain,index,mes.getTerms(),mes);
	}
	
	public void goRecommand(UserDialog userDialog, String aimDomain, int index,
			List<Term> terms,UserMessage mes) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < terms.size(); i++) {
			if (i == index) {
				builder.append(aimDomain);
			} else {
				builder.append(terms.get(i));
			}
		}
		mes.setMessage(builder.toString());
		userDialog.setState(DialogState.START);
		//开始之前进行数据重置（清除旧的结果）
		UserMessageDataFiller.getInstance().dataReset(mes);
		StartCmd.getInstance().doRun(mes, null);
	}
	
	public void sendMsg(String output,UserMessage mes){
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setAudioText(output);
		RobotConsumerListener.getInstance().sendMsg(responseMessage,mes.getReqMessage());
	}
	
	/**
	 * 用户没有给出想要的答案，进入待放弃状态
	 * @param mes
	 * @param userDialog
	 * @param message
	 */
	public void goPreQuitState(UserMessage mes,UserDialog userDialog,ResponseMessage message){
		userDialog.setState(DialogState.PREQUIT);
		RobotConsumerListener.getInstance().sendMsg(message,mes.getReqMessage());
	}
	
	/**
	 * 进入开始状态
	 * @param mes
	 * @param userDialog
	 * @param message
	 */
	public void goStartState(UserMessage mes,UserDialog userDialog,ResponseMessage message){
		userDialog.setState(DialogState.START);
		RobotConsumerListener.getInstance().sendMsg(message,mes.getReqMessage());
	}
	
	/**
	 * 用户取消撤销话题
	 * @param mes
	 * @param userDialog
	 */
	public void cancelQuit(UserMessage mes,UserDialog userDialog){
		userDialog.setState(userDialog.getPreDialogState());
		RobotConsumerListener.getInstance().sendMsg(userDialog.getResMessages().get(userDialog.getResMessages().size()-1),mes.getReqMessage());
	}
}
