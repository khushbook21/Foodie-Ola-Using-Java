package com.foodieola.session;

public class LoginBlueprint {
	
	private String username;
	private String password;
	private double lattitude;
	private double longitude;
	public LoginBlueprint(String username, String password, double lattitude, double longitude) {
		this.username = username;
		this.password = password;
		this.lattitude = lattitude;
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
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "LoginBlueprint [username=" + username + ", password=" + password + ", lattitude=" + lattitude
				+ ", longitude=" + longitude + "]";
	}

	
}
