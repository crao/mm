package com.my.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	private long userId; 
	private String fileName;
	private String type;

	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	

	public Photo(){}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;	
	}

	public long getUserId() {
		return userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}
