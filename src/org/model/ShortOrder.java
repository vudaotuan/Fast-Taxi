package org.model;

public class ShortOrder {
	private int customer_id;
	private int taxi_id;

	private int lat_taxi;
	private int lon_taxi;

	private int lat_customer;
	private int lon_customer;
	
	public ShortOrder(String jsonString) {
	}

	public ShortOrder(int customer_id, int taxi_id, int lat_taxi, int lon_taxi,
			int lat_customer, int lon_customer) {
		super();
		this.customer_id = customer_id;
		this.taxi_id = taxi_id;
		this.lat_taxi = lat_taxi;
		this.lon_taxi = lon_taxi;
		this.lat_customer = lat_customer;
		this.lon_customer = lon_customer;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getTaxi_id() {
		return taxi_id;
	}

	public void setTaxi_id(int taxi_id) {
		this.taxi_id = taxi_id;
	}

	public int getLat_taxi() {
		return lat_taxi;
	}

	public void setLat_taxi(int lat_taxi) {
		this.lat_taxi = lat_taxi;
	}

	public int getLon_taxi() {
		return lon_taxi;
	}

	public void setLon_taxi(int lon_taxi) {
		this.lon_taxi = lon_taxi;
	}

	public int getLat_customer() {
		return lat_customer;
	}

	public void setLat_customer(int lat_customer) {
		this.lat_customer = lat_customer;
	}

	public int getLon_customer() {
		return lon_customer;
	}

	public void setLon_customer(int lon_customer) {
		this.lon_customer = lon_customer;
	}

	public boolean is_going_closed(){
		return true;
	}
}
