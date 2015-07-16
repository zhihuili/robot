package com.nana.serviceengine.domain.cookbook.bean;

public class FoodSteps {
	private String img;
	private String step;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return img+" "+step;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
	
	
}
