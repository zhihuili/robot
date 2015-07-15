package com.nana.serviceengine.domain.joke.bean;

public class Joke {
	String content;
	String hashId;
	String unixtime;
	String updatetime;

//	public Joke(String content, String hashId, String unixtime,
//			String updatetime) {
//		this.content = content;
//		this.hashId = hashId;
//		this.unixtime = unixtime;
//		this.updatetime = updatetime;
//	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	public String getUnixtime() {
		return unixtime;
	}

	public void setUnixtime(String unixtime) {
		this.unixtime = unixtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
}