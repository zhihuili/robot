package com.nana.serviceengine.domain.location.bean;

/*
 * position info
 */
public class Position {
	private String address;
	private String business;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return address + " " + business;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
}
