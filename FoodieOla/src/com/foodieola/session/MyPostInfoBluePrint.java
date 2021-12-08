package com.foodieola.session;

public class MyPostInfoBluePrint {

	private String t_usernme;
	private String plate;
	private String foodType;
	private String g_phone;
	private String mode;
	private String g_name;
	private String created_At;
	public MyPostInfoBluePrint(String t_usernme, String plate, String foodType, String g_phone, String mode,
			String g_name, String created_At) {
		this.t_usernme = t_usernme;
		this.plate = plate;
		this.foodType = foodType;
		this.g_phone = g_phone;
		this.mode = mode;
		this.g_name = g_name;
		this.created_At = created_At;
	}
	public String getT_usernme() {
		return t_usernme;
	}
	public void setT_usernme(String t_usernme) {
		this.t_usernme = t_usernme;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getG_phone() {
		return g_phone;
	}
	public void setG_phone(String g_phone) {
		this.g_phone = g_phone;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getCreated_At() {
		return created_At;
	}
	public void setCreated_At(String created_At) {
		this.created_At = created_At;
	}
	
	
}
