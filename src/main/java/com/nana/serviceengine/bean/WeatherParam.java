package com.nana.serviceengine.bean;

import java.util.Date;

public class WeatherParam {
	private String location;
	private Date date;
	
	private String gpslon;
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

	public String getGpslon() {
		return gpslon;
	}

	public void setGpslon(String gpslon) {
		this.gpslon = gpslon;
	}

	public String getGpslat() {
		return gpslat;
	}

	public void setGpslat(String gpslat) {
		this.gpslat = gpslat;
	}
	
	

}
