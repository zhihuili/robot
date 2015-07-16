package com.nana.serviceengine.domain.cookbook.bean;
import java.util.List;

public class Cookbook {
	private String title;
	private String tags;
	private String imtrol;
	private String ingredients;
	private String burden;
	private String albums;
	private List<FoodSteps> steps;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title+" "+tags+" "+imtrol+" "+ingredients+" "+burden+" "+albums+" "+steps;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getImtrol() {
		return imtrol;
	}
	public void setImtrol(String imtrol) {
		this.imtrol = imtrol;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getBurden() {
		return burden;
	}
	public void setBurden(String burden) {
		this.burden = burden;
	}
	public String getAlbums() {
		return albums;
	}
	public void setAlbums(String albums) {
		this.albums = albums;
	}
	public List<FoodSteps> getSteps() {
		return steps;
	}
	public void setSteps(List<FoodSteps> steps) {
		this.steps = steps;
	}
	
	
	
}
