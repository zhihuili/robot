package com.nana.serviceengine.domain.commonapi.map.bean;

/**
 * 详细地址信息
 * @author wds
 *
 */
public class Address {
	//结构化的地址信息
	private String formatted_address;
	//城市名
	private String city;
	//国家
	private String country;
	//区县名
	private String district;
	//省
	private String province;
	//街道名
	private String street;
	//街道门牌号
	private String street_number;
	
	
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	@Override
	public String toString() {
		return "Address [formatted_address=" + formatted_address + ", city="
				+ city + ", country=" + country + ", district=" + district
				+ ", province=" + province + ", street=" + street
				+ ", street_number=" + street_number + "]";
	}
	
	
	
}
