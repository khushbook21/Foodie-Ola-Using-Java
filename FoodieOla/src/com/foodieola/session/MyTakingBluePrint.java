package com.foodieola.session;

public class MyTakingBluePrint {
 
	private String username;
	private String noOfPlate;
	private String pickingType;
	private String foodType;
	private String cretedAt;
	private String id;
	public MyTakingBluePrint(String username, String noOfPlate, String pickingType, String foodType, String cretedAt,
			String id) {
		this.username = username;
		this.noOfPlate = noOfPlate;
		this.pickingType = pickingType;
		this.foodType = foodType;
		this.cretedAt = cretedAt;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
