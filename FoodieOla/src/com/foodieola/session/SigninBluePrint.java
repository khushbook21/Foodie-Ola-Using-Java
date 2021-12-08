package com.foodieola.session;

public class SigninBluePrint {
	
	private String username;
	private String password;
	private String latitude;
	private String longitude;
	public SigninBluePrint(String username, String password, String latitude, String longitude) {
		this.username = username;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public String toString() {
		return "SigninBluePrint [username=" + username + ", password=" + password + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	

}
