package org.model;

public class Car {
	private int id;
	private int deviceID;
	private int price;
	private int allocation;
	private String type;
	private int longitude;
	private int latitude;

	public Car(int id, int deviceID, int price, int allocation, String type,
			int longitude, int latitude) {
		super();
		this.id = id;
		this.deviceID = deviceID;
		this.price = price;
		this.allocation = allocation;
		this.type = type;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAllocation() {
		return allocation;
	}

	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

}
