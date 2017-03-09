package com.my.binding;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class Register {

	private long memberId;

	private String brandName;

	private String email;

	private String password;
	
	private String mobileNo;
	
	private String WorkingHours;

	private String WorkingDays;

	private String city;
	@NotNull
	private String category;
	
	
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getWorkingHours() {
		return WorkingHours;
	}
	public void setWorkingHours(String workingHours) {
		WorkingHours = workingHours;
	}
	public String getWorkingDays() {
		return WorkingDays;
	}
	public void setWorkingDays(String workingDays) {
		WorkingDays = workingDays;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
