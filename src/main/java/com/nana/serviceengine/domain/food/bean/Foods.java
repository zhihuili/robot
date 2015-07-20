package com.nana.serviceengine.domain.food.bean;

public class Foods {
	private String name;
	private String navigation;
	private String city;
	private String area;
	private String address;
	private String phone;
	private String latitude;
	private String longitude;
	private String stars;
	private String avg_price;
	private String photos;
	private String tags;
	private String all_remarks;
	private String very_good_remarks;
	private String good_remarks;
	private String common_remarks;
	private String bad_remarks;
	private String very_bad_remarks;
	private String product_rating;
	private String environment_rating;
	private String service_rating;
	private String recommended_products;
	private String recommended_dishes;
	private String nearby_shops;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" "+navigation+" "+city+" "+area+" "+address+" "+phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(String avg_price) {
		this.avg_price = avg_price;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAll_remarks() {
		return all_remarks;
	}

	public void setAll_remarks(String all_remarks) {
		this.all_remarks = all_remarks;
	}

	public String getVery_good_remarks() {
		return very_good_remarks;
	}

	public void setVery_good_remarks(String very_good_remarks) {
		this.very_good_remarks = very_good_remarks;
	}

	public String getGood_remarks() {
		return good_remarks;
	}

	public void setGood_remarks(String good_remarks) {
		this.good_remarks = good_remarks;
	}

	public String getCommon_remarks() {
		return common_remarks;
	}

	public void setCommon_remarks(String common_remarks) {
		this.common_remarks = common_remarks;
	}

	public String getBad_remarks() {
		return bad_remarks;
	}

	public void setBad_remarks(String bad_remarks) {
		this.bad_remarks = bad_remarks;
	}

	public String getVery_bad_remarks() {
		return very_bad_remarks;
	}

	public void setVery_bad_remarks(String very_bad_remarks) {
		this.very_bad_remarks = very_bad_remarks;
	}

	public String getProduct_rating() {
		return product_rating;
	}

	public void setProduct_rating(String product_rating) {
		this.product_rating = product_rating;
	}

	public String getEnvironment_rating() {
		return environment_rating;
	}

	public void setEnvironment_rating(String environment_rating) {
		this.environment_rating = environment_rating;
	}

	public String getService_rating() {
		return service_rating;
	}

	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}

	public String getRecommended_products() {
		return recommended_products;
	}

	public void setRecommended_products(String recommended_products) {
		this.recommended_products = recommended_products;
	}

	public String getRecommended_dishes() {
		return recommended_dishes;
	}

	public void setRecommended_dishes(String recommended_dishes) {
		this.recommended_dishes = recommended_dishes;
	}

	public String getNearby_shops() {
		return nearby_shops;
	}

	public void setNearby_shops(String nearby_shops) {
		this.nearby_shops = nearby_shops;
	}

}
