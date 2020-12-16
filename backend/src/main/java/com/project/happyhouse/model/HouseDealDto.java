package com.project.happyhouse.model;

public class HouseDealDto {
	/** 거래 정보를 식별할 번호 */
	private int no;
	/** 아파트 이름 */
	private String aptName;
	/** 법정 동명 */
	private String dong;
	/** 지번 */
	private String jibun;
	/** 층 */
	private String floor;
	/** 타입 */
	private String type;
	/** 군구코드 */
	private String gunguCode;
	/** 거래 금액 */
	private String dealAmount;
	/** 건축 연도 */
	private String buildYear;
	/** 전용면적 */
	private String area;
	/** 위도 */
	private String lat;
	/** 경도 */
	private String lng;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGunguCode() {
		return gunguCode;
	}

	public void setGunguCode(String gunguCode) {
		this.gunguCode = gunguCode;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	@Override
	public String toString() {
		return "HouseDealDto [no=" + no + ", aptName=" + aptName + ", dong=" + dong + ", jibun=" + jibun + ", floor="
				+ floor + ", type=" + type + ", gunguCode=" + gunguCode + ", dealAmount=" + dealAmount + ", buildYear="
				+ buildYear + ", area=" + area + ", lat=" + lat + ", lng=" + lng + "]";
	}

}
