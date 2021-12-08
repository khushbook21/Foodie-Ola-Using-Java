package com.foodieola.session;

public class MyGivingsBluePrint {

	private String id;
	private String taker;
	private String foodType;
	private String plate;
	private String pick;
	private String taker_accept;
	private String otp;
	private String created_at;
	
	
	public MyGivingsBluePrint(String id, String taker, String foodType, String plate, String pick, String taker_accept,
			String otp, String created_at) {
		this.id = id;
		this.taker = taker;
		this.foodType = foodType;
		this.plate = plate;
		this.pick = pick;
		this.taker_accept = taker_accept;
		this.otp = otp;
		this.created_at = created_at;
	}
	
	
	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaker() {
		return taker;
	}
	public void setTaker(String taker) {
		this.taker = taker;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getPick() {
		return pick;
	}
	public void setPick(String pick) {
		this.pick = pick;
	}
	public String getTaker_accept() {
		return taker_accept;
	}
	public void setTaker_accept(String taker_accept) {
		this.taker_accept = taker_accept;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "MyGivingsBluePrint [id=" + id + ", taker=" + taker + ", foodType=" + foodType + ", plate=" + plate
				+ ", pick=" + pick + ", taker_accept=" + taker_accept + ", otp=" + otp + "]";
	}
	
	
}
