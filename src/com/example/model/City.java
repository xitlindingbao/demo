package com.example.model;

/**
 * City的实体类，方便对City的操作
 * 
 * @author lindb
 * @time 2017.11.4
 * 
 */
public class City {
	private int id;
	private String cityName;
	private String cityCode;
	private int provincdId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getProvinceId() {
		return provincdId;
	}

	public void setProvinceId(int provinceId) {
		this.provincdId = provinceId;
	}
}
