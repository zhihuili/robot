package com.nana.serviceengine.domain.video.bean;

import java.util.List;

public class Movie {
	private String imageUrl;
	private String info;
	private List<MovieKeyWord> keywordCountList;
	private String name;
	private double score;
	private int subjectId;
	private String summary;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<MovieKeyWord> getKeywordCountList() {
		return keywordCountList;
	}

	public void setKeywordCountList(List<MovieKeyWord> keywordCountList) {
		this.keywordCountList = keywordCountList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "imageUrl=" + imageUrl + "   " + "info=" + info + "   "
				+ "keywordCountList=" + keywordCountList + "   " + "name="
				+ name + "   " + "score=" + score + "   " + "subjectId="
				+ subjectId + "   " + "summary=" + summary;
	}
}
