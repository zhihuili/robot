package com.nana.serviceengine.webapi.bean;

public class MovieKeyWord {
	private int count;
	private String key;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getKey() { 
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "count=" + count + " " + "key=" + key;
	}
}
