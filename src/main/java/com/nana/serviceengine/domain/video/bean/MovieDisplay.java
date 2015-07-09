package com.nana.serviceengine.domain.video.bean;

public class MovieDisplay {
	private String imageUrl;
	private String keywordCountList;
	private String name;
	private String score;
	private String summary;
	
	private Movie movie;
	//这个必须要掉入
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getImageUrl() {
		return movie.getImageUrl();
	}
	public String getKeywordCountList() {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<movie.getKeywordCountList().size();i++){
			builder.append(movie.getKeywordCountList().get(i).getKey()+"|");
		}
		if(builder.length() > 0) builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}

	public String getName() {
		return movie.getName();
	}
	
	public String getScore() {
		return movie.getScore()+"";
	}
	
	public String getSummary() {
		return movie.getSummary();
	}
}
