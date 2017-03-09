package com.my.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VendorReg" , uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long memberId;
	@NotNull
	private String brandName;
	@NotNull
	@Column(unique=true)
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String mobileNo;
	@NotNull
	private String WorkingHours;
	@NotNull
	private String WorkingDays;
	@NotNull
	private String city;
	@NotNull
	private String category;

	
	//@OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
	//@PrimaryKeyJoinColumn
	

	

	public Member(long id,String brandName, String city) {
		this.memberId = id;
		this.brandName = brandName;
		this.city = city;
	
	}
	
	public Member() {}

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
