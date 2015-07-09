package com.nana.serviceengine.inout.bean;

public class ResponseDisplay {
	private String dataType;//0:普通文本 1: html
	private String height; //App段webView控件的高度，值为百分比
	private String content;
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
