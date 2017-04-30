package com.my.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long memId;
	private String name;
	public long getId() {
		return id;
	}
	public long getMemId() {
		return memId;
	}
	public String getName() {
		return name;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setMemId(long memId) {
		this.memId = memId;
	}
	public void setName(String name) {
		this.name = name;
	}

}
