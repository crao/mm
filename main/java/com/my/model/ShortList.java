package com.my.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shortlists")
public class ShortList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	long memId;
	long slId;
	Date shortListedOn;
	
	
	public long getId() {
		return id;
	}
	public long getMemId() {
		return memId;
	}
	public long getSlId() {
		return slId;
	}
	public Date getShortListedOn() {
		return shortListedOn;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setMemId(long memId) {
		this.memId = memId;
	}
	public void setSlId(long slId) {
		this.slId = slId;
	}
	public void setShortListedOn(Date shortListedOn) {
		this.shortListedOn = shortListedOn;
	}

}
