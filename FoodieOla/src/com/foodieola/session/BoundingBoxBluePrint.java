package com.foodieola.session;

public class BoundingBoxBluePrint {

	private String latUp;
	private String latDown;
	private String longUp;
	private String longDown;
	
	public BoundingBoxBluePrint(String longUp,String longDown,String latUp,String latDown) {
		this.latUp = latUp;
		this.latDown = latDown;
		this.longUp = longUp;
		this.longDown = longDown;
	}
	public String getLatUp() {
		return latUp;
	}
	public void setLatUp(String latUp) {
		this.latUp = latUp;
	}
	public String getLatDown() {
		return latDown;
	}
	public void setLatDown(String latDown) {
		this.latDown = latDown;
	}
	public String getLongUp() {
		return longUp;
	}
	public void setLongUp(String longUp) {
		this.longUp = longUp;
	}
	public String getLongDown() {
		return longDown;
	}
	public void setLongDown(String longDown) {
		this.longDown = longDown;
	}
	@Override
	public String toString() {
		return "boundingBoxBluePrint [latUp=" + latUp + ", latDown=" + latDown + ", longUp=" + longUp + ", longDown="
				+ longDown + "]";
	}
	
	
}
