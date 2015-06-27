package com.nana.serviceengine.processor.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;

import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.bean.UserDialog;
import com.nana.serviceengine.bean.VideoParam;
import com.nana.serviceengine.cacher.UserAnswer;
import com.nana.serviceengine.cacher.UserTheme;
import com.nana.serviceengine.dic.DomainDic;
import com.nana.serviceengine.grammer.analyzer.GrammerAnalyzer;
import com.nana.serviceengine.grammer.bean.GrammerItem;
import com.nana.serviceengine.grammer.bean.ObjectItem;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.sentence.impl.VideoSentenceCreator;
import com.nana.serviceengine.sentence.impl.WeatherSentenceCreator;
import com.nana.serviceengine.webapi.APIAccessor;
import com.nana.serviceengine.webapi.impl.VideoAPI;

public class VideoServiceProcessor extends ServiceProcessor {

	@Override
	public void run() {
		// 如果缺少属性则构造答案
		String unfinishedanswer = null;
		boolean isfinished = false;
		VideoParam vParam = domainObject == null ? new VideoParam()
				: (VideoParam) domainObject;
		StringBuilder builder = new StringBuilder();
		UserDialog userDialog = UserTheme.UserDialog.get(mes.getUserid());
		if (userDialog.getCount() == 1) {
			List<ObjectItem> obItems = mes.getGrammerItem().getObjects();
			for (int i = 0; i < obItems.size(); i++) {
				DomainKeyWord tmpSubDomain = DomainDic.domainKeyWord
						.get(obItems.get(i).getValue());
				if (tmpSubDomain != null
						&& "video".equals(tmpSubDomain.getDomain())) {
					for (String str : obItems.get(i).getAttributive()) {
						builder.append(str + " ");
					}
				}
			}
		} else {
			for (Term term : mes.getTerms()) {
				char nature = term.getNatureStr().charAt(0);
				if ('n' == nature || 'a' == nature) {
					builder.append(term.getRealName() + " ");
				}
			}
		}
		if (builder.length() == 0) {
			unfinishedanswer = "请问，您想看关于什么的电影?";
		} else {
			builder.deleteCharAt(builder.length() - 1);
			vParam.setKeyWord(builder.toString());
			isfinished = true;
		}
		int count = userDialog.getCount() + 1;
		userDialog.setCount(count);
		if (isfinished) {
			finalDealRequest(vParam);
		} else {
			
			sendMsg(unfinishedanswer);
		}

	}

	private void finalDealRequest(VideoParam vParam) {

		APIAccessor apiAccessor = VideoAPI.getInstance();

		String data = null;
		try {
			data = new String(apiAccessor.getInfo(vParam)
					.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", data);
		sendMsg(VideoSentenceCreator.getInstance().createSentence(params));

	}

}
