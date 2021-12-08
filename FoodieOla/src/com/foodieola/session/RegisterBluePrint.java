package com.foodieola.session;

public class RegisterBluePrint {

	private String username;
	private String email;
	private String password;
	private String phone;
	private String latitude;
	private String longitude;
	public RegisterBluePrint(String username, String email, String password, String phone, String latitude,
			String longitude) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public String toString() {
		return "RegisterBluePrint [username=" + username + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
