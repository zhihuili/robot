package com.nana.serviceengine.bean;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.common.message.RequestMessage;
import com.nana.serviceengine.grammer.bean.GrammerItem;

public class UserMessage {

	private String userid;
	private String message;
	private List<Term> terms;
	GrammerItem grammerItem;
	RequestMessage reqMessage;
	private String lon;
	private String lat;
	
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

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
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

	
	
	
	
	
}
