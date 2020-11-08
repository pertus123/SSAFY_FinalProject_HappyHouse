package com.project.happyhouse.model;

public class HospitalDto {
	int hospitalId;
	String gunguCode;
	String name;
	String address;
	String weekdayop;
	String tel;
	String lat;
	String lng;
	String type;

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getGunguCode() {
		return gunguCode;
	}

	public void setGunguCode(String gunguCode) {
		this.gunguCode = gunguCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeekdayop() {
		return weekdayop;
	}

	public void setWeekdayop(String weekdayop) {
		this.weekdayop = weekdayop;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "HospitalDto [hospitalId=" + hospitalId + ", gunguCode=" + gunguCode + ", name=" + name + ", address="
				+ address + ", weekdayop=" + weekdayop + ", tel=" + tel + ", lat=" + lat + ", type=" + type + "]";
	}
}
