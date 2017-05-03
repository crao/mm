package com.my.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LifeStyle" )
public class Lifestyle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String hobbiesAndIntereset;
	private String  favouriteMusic;
	private String otherMusic;
	private String otherHobby;
	private String  sportsFitness;
	private String otherSports;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHobbiesAndIntereset() {
		return hobbiesAndIntereset;
	}
	public void setHobbiesAndIntereset(String hobbiesAndIntereset) {
		this.hobbiesAndIntereset = hobbiesAndIntereset;
	}
	public String getFavouriteMusic() {
		return favouriteMusic;
	}
	public void setFavouriteMusic(String favouriteMusic) {
		this.favouriteMusic = favouriteMusic;
	}
	public String getOtherMusic() {
		return otherMusic;
	}
	public void setOtherMusic(String otherMusic) {
		this.otherMusic = otherMusic;
	}
	public String getOtherHobby() {
		return otherHobby;
	}
	public void setOtherHobby(String otherHobby) {
		this.otherHobby = otherHobby;
	}
	public String getSportsFitness() {
		return sportsFitness;
	}
	public void setSportsFitness(String sportsFitness) {
		this.sportsFitness = sportsFitness;
	}
	public String getOtherSports() {
		return otherSports;
	}
	public void setOtherSports(String otherSports) {
		this.otherSports = otherSports;
	}

	@OneToOne
	@JoinColumn(name="memberId")
	private Member member;
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
}
