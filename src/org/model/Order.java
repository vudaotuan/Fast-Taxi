package org.model;

public class Order {

	private int id;
	private int user_id;
	private int car_id;
	private int user_latitude;
	private int user_longitude;
	private int car_latitude;
	private int car_longitude;
	private int status;

	public Order() {
		super();
	}

	public Order(int id, int user_id, int car_id, int user_latitude,
			int user_longitude, int car_latitude, int car_longitude, int status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.car_id = car_id;
		this.user_latitude = user_latitude;
		this.user_longitude = user_longitude;
		this.car_latitude = car_latitude;
		this.car_longitude = car_longitude;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public int getUser_latitude() {
		return user_latitude;
	}

	public void setUser_latitude(int user_latitude) {
		this.user_latitude = user_latitude;
	}

	public int getUser_longitude() {
		return user_longitude;
	}

	public void setUser_longitude(int user_longitude) {
		this.user_longitude = user_longitude;
	}

	public int getCar_latitude() {
		return car_latitude;
	}

	public void setCar_latitude(int car_latitude) {
		this.car_latitude = car_latitude;
	}

	public int getCar_longitude() {
		return car_longitude;
	}

	public void setCar_longitude(int car_longitude) {
		this.car_longitude = car_longitude;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
