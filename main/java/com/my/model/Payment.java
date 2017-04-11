package com.my.model;

import java.util.Date;

public class Payment {
	
	private long paymentId;
	private long memberId;
	private Date paidOn;
	private Date validUntil; 
	private int balance; 
	private String currency;
	private PaymentMode paymentMode;
	private String override;
	private String cardNumber;
	
	public long getPaymentId() {
		return paymentId;
	}
	public long getMemberId() {
		return memberId;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public int getBalance() {
		return balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}
	public String getOverride() {
		return override;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	public void setOverride(String override) {
		this.override = override;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
