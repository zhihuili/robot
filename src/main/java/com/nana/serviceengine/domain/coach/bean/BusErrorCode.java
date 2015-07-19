package com.nana.serviceengine.domain.coach.bean;

import java.util.List;

public class BusErrorCode {
	String reason;
	String result;
	List<Bus> list;
	String error_code;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<Bus> getList() {
		return list;
	}
	public void setList(List<Bus> list) {
		this.list = list;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	

}
