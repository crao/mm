package com.my.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long fromMemId; 
	
	private long toMemId;
	
	@Column(length = 65535,columnDefinition="Text")
	private String message;

	public long getId() {
		return id;
	}

	public long getFromMemId() {
		return fromMemId;
	}

	public long getToMemId() {
		return toMemId;
	}

	public String getMessage() {
		return message;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFromMemId(long fromMemId) {
		this.fromMemId = fromMemId;
	}

	public void setToMemId(long toMemId) {
		this.toMemId = toMemId;
	}

	public void setMessage(String message) {
		this.message = message;
	} 

}
