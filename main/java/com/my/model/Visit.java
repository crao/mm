package com.my.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "visit")
public class Visit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long memId;
	private long visitor;
	private Date lastVisit;
	public long getId() {
		return id;
	}
	public long getMemId() {
		return memId;
	}
	public long getVisitor() {
		return visitor;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setMemId(long memId) {
		this.memId = memId;
	}
	public void setVisitor(long visitor) {
		this.visitor = visitor;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

}
