package com.nana.serviceengine.common.bean;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.common.message.RequestMessage;
import com.nana.serviceengine.grammer.bean.GrammerItem;

public class UserMessage {

	private String userid;
	private String message;
	private List<Term> terms;
	private List<String[]> domainKeyWords;
	GrammerItem grammerItem;
	RequestMessage reqMessage;
	private GPS gps;
	
	
	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	public GrammerItem getGrammerItem() {
		return grammerItem;
	}

	public void setGrammerItem(GrammerItem grammerItem) {
		this.grammerItem = grammerItem;
	}


	public RequestMessage getReqMessage() {
		return reqMessage;
	}

	public void setReqMessage(RequestMessage reqMessage) {
		this.reqMessage = reqMessage;
	}

	public List<String[]> getDomainKeyWords() {
		return domainKeyWords;
	}

	public void setDomainKeyWords(List<String[]> domainKeyWords) {
		this.domainKeyWords = domainKeyWords;
	}	
	
}
