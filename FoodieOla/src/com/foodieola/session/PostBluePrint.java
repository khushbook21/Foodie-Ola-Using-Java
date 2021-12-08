package com.foodieola.session;

public class PostBluePrint {
	
	private String username;
	private String foodType;
	private String noOfPlate;
	private String pickingType;
	private String deleteAt;
	private String fixed;
	private String latitude;
	private String longitude;
	public PostBluePrint(String username, String foodType, String noOfPlate, String pickingType, String deleteAt,
			String fixed,String validity, String latitude, String longitude) {
		this.username = username;
		this.foodType = foodType;
		this.noOfPlate = noOfPlate;
		this.pickingType = pickingType;
		this.deleteAt = deleteAt;
		this.fixed = fixed;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
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
	public String getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
	public String getFixed() {
		return fixed;
	}
	public void setFixed(String fixed) {
		this.fixed = fixed;
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
		return "PostBluePrint [username=" + username + ", foodType=" + foodType + ", noOfPlate=" + noOfPlate
				+ ", pickingType=" + pickingType + ", deleteAt=" + deleteAt + ", fixed=" + fixed + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	

}
