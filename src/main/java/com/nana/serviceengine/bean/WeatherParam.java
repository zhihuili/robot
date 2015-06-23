package com.nana.serviceengine.bean;

import java.util.Date;

public class WeatherParam {
	private String location;
	private Date date;
	
	private String gpslot;
	private String gpslat;
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGpslot() {
		return gpslot;
	}

	public void setGpslot(String gpslot) {
		this.gpslot = gpslot;
	}

	public String getGpslat() {
		return gpslat;
	}

	public void setGpslat(String gpslat) {
		this.gpslat = gpslat;
	}
	
	

}
