package com.my.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="personaldetails")
public class PersonalDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long userId;
	private String maritalStatus;
	private int height;
	private int weight;
	private String bodyType;
	private String complexion;
	private String physicalStatus;
	private String education;
	private String occupation;
	private String occupationcategory;
	private String income; 
	private String incomecurrency;
	
	private String eatingHabits;
	private String drinkingHabits;
	private String smokingHabits;
	
	private String gothra;
	private String manglik;
	private String starSign;
	private String raasi; 
	private String dosh;
	private String familyStatus;
	private String familyType;
	private String familyValues;
	
	private String residingstate;
	private String residingcity;
	private String country;
	private String citizenship;
	private String fatherName;
	private String motherName;
	private String countryP;

	private String residingstateP;
	private String residingcityP;
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getCountryP() {
		return countryP;
	}

	public void setCountryP(String countryP) {
		this.countryP = countryP;
	}

	public String getResidingstateP() {
		return residingstateP;
	}

	public void setResidingstateP(String residingstateP) {
		this.residingstateP = residingstateP;
	}

	public String getResidingcityP() {
		return residingcityP;
	}

	public void setResidingcityP(String residingcityP) {
		this.residingcityP = residingcityP;
	}

	@OneToOne
	@JoinColumn(name="memberId")
	private Member member;
	
	public PersonalDetails() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
	public String getPhysicalStatus() {
		return physicalStatus;
	}

	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
	}

	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOccupationcategory() {
		return occupationcategory;
	}

	public void setOccupationcategory(String occupationcategory) {
		this.occupationcategory = occupationcategory;
	}

	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getIncomePeriod() {
		return incomecurrency;
	}
	public void setIncomePeriod(String incomePeriod) {
		this.incomecurrency = incomePeriod;
	}
	
	public String getEatingHabits() {
		return eatingHabits;
	}
	public void setEatingHabits(String eatingHabits) {
		this.eatingHabits = eatingHabits;
	}
	public String getDrinkingHabits() {
		return drinkingHabits;
	}
	public void setDrinkingHabits(String drinkingHabits) {
		this.drinkingHabits = drinkingHabits;
	}
	public String getSmokingHabits() {
		return smokingHabits;
	}
	public void setSmokingHabits(String smokingHabits) {
		this.smokingHabits = smokingHabits;
	}
	public String getGothra() {
		return gothra;
	}

	public void setGothra(String gothra) {
		this.gothra = gothra;
	}

	public String getManglik() {
		return manglik;
	}

	public void setManglik(String manglik) {
		this.manglik = manglik;
	}

	public String getStarSign() {
		return starSign;
	}
	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}
	public String getRaasi() {
		return raasi;
	}
	public void setRaasi(String raasi) {
		this.raasi = raasi;
	}
	public String getDosh() {
		return dosh;
	}
	public void setDosh(String dosh) {
		this.dosh = dosh;
	}
	public String getFamilyStatus() {
		return familyStatus;
	}
	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}
	public String getFamilyValues() {
		return familyValues;
	}
	public void setFamilyValues(String familyValues) {
		this.familyValues = familyValues;
	}
	
	//@OneToOne(mappedBy="personalDetails")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

	public String getIncomecurrency() {
		return incomecurrency;
	}

	public void setIncomecurrency(String incomecurrency) {
		this.incomecurrency = incomecurrency;
	}

	public String getResidingstate() {
		return residingstate;
	}

	public void setResidingstate(String residingstate) {
		this.residingstate = residingstate;
	}

	public String getResidingcity() {
		return residingcity;
	}

	public void setResidingcity(String residingcity) {
		this.residingcity = residingcity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
}
