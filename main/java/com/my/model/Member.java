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
@Table(name = "members" , uniqueConstraints={@UniqueConstraint(columnNames={"firstname", "lastname","email"})})
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long memberId;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Column(unique=true)
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String profileFor;
	@NotNull
	private Date dob;
	private int age;
	@NotNull
	private String gender;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String mobile;
	private int countryCode;
	private boolean profilePic;
	
	//@OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
	//@PrimaryKeyJoinColumn
	@OneToOne(mappedBy="member")
	private PersonalDetails personalDetails;
	
	@OneToOne(mappedBy="member")
	private Preferences preferences;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="member")
	private List<Photo> photos;

	public Member(long id,String firstName, String lastName, int age, String gender) {
		this.memberId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}
	
	public Member() {}

	
	public long getmemberId() {
		return memberId;
	}

	public void setId(long id) {
		this.memberId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getProfileFor() {
		return profileFor;
	}

	public void setProfileFor(String profileFor) {
		this.profileFor = profileFor;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="personalDetailsId")
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public boolean isProfilePic() {
		return profilePic;
	}

	public void setProfilePic(boolean profilePic) {
		this.profilePic = profilePic;
	}

	
	
	
}
