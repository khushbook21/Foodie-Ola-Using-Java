package com.foodieola.session;

public class ProfileBluePrint {

	private String username;
	private String email;
	private String latitude;
	private String longitude;
	private String phone;
	public ProfileBluePrint(String username, String email, String latitude, String longitude, String phone) {
		this.username = username;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
