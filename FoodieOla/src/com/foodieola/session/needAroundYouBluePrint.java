package com.foodieola.session;

public class needAroundYouBluePrint {
	
	private String username;
	private String noOfPlate;
	private String pickingType;
	private String foodType;
	private String cretedAt;
	private String contact;
	private String latitude;
	private String longitude;
	private int id;
	public needAroundYouBluePrint(int id, String username, String noOfPlate,String foodType, String pickingType, 
			String cretedAt, String contact, String latitude, String longitude) {
		this.username = username;
		this.noOfPlate = noOfPlate;
		this.pickingType = pickingType;
		this.foodType = foodType;
		this.cretedAt = cretedAt;
		this.contact = contact;
		this.latitude = latitude;
		this.longitude = longitude;
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNoOfPlate() {
		return noOfPlate;
	}
	public void setNoOfPlate(String noOfPlate) {
		this.noOfPlate = noOfPlate;
	}
	public String getPickingType() {
		return pickingType;
	}
	public void setPickingType(String pickingType) {
		this.pickingType = pickingType;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getCretedAt() {
		return cretedAt;
	}
	public void setCretedAt(String cretedAt) {
		this.cretedAt = cretedAt;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "needAroundYouBluePrint [username=" + username + ", noOfPlate=" + noOfPlate + ", pickingType="
				+ pickingType + ", foodType=" + foodType + ", cretedAt=" + cretedAt + ", contact=" + contact
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", id=" + id + "]";
	}
	
	
	
	
}
