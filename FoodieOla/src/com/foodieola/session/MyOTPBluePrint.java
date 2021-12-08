package com.foodieola.session;

public class MyOTPBluePrint {

	private String OTP;
	private String giver;
	private String created_At;
	public MyOTPBluePrint(String oTP, String giver, String created_At) {
		OTP = oTP;
		this.giver = giver;
		this.created_At = created_At;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	public String getGiver() {
		return giver;
	}
	public void setGiver(String giver) {
		this.giver = giver;
	}
	public String getCreated_At() {
		return created_At;
	}
	public void setCreated_At(String created_At) {
		this.created_At = created_At;
	}
	
	
}
