package org.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "id")
	private int id;
	@XmlElement(name = "name")
	private String name;
	private String password;
	private int deviceID;
	private int longitude;
	private int latitude;

	public User() {

	}

	public User(int id, String name, String password, int deviceID,
			int longitude, int latitude) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.deviceID = deviceID;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
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
