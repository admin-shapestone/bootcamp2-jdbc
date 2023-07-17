package com.shapestone.banking_domin_jdbc;

import java.util.Date;

public class Payments_Pojo {

	private long paymentId;
	private long accountId;
	private String purposeOfPayment;
	private double amountPaid;
	private double amountRecived;
	private String dateOfPayment;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getPurposeOfPayment() {
		return purposeOfPayment;
	}

	public void setPurposeOfPayment(String purposeOfPayment) {
		this.purposeOfPayment = purposeOfPayment;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountRecived() {
		return amountRecived;
	}

	public void setAmountRecived(double amountRecived) {
		this.amountRecived = amountRecived;
	}

	public String getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	@Override
	public String toString() {
		return "Payments_Pojo [paymentId=" + paymentId + ", accountId=" + accountId + ", purposeOfPayment="
				+ purposeOfPayment + ", amountPaid=" + amountPaid + ", amountRecived=" + amountRecived
				+ ", dateOfPayment=" + dateOfPayment + "]";
	}

}