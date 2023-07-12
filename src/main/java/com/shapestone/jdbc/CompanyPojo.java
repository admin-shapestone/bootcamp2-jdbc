package com.shapestone.jdbc;

public class CompanyPojo {
	private int empId;
	private int companyId;
	private String emailId;
	private String dateOfReleving;
	private double amountReceived;
	private String companyName;
	private double ctcTimeOfReleving;
	private String dateOfJoining;
	@Override
	public String toString() {
		return "CompanyPojo [empId=" + empId + ", companyId=" + companyId + ", emailId=" + emailId + ", dateOfReleving="
				+ dateOfReleving + ", amountReceived=" + amountReceived + ", companyName=" + companyName
				+ ", ctcTimeOfReleving=" + ctcTimeOfReleving + ", dateOfJoining=" + dateOfJoining + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDateOfReleving() {
		return dateOfReleving;
	}
	public void setDateOfReleving(String dateOfReleving) {
		this.dateOfReleving = dateOfReleving;
	}
	public double getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getCtcTimeOfReleving() {
		return ctcTimeOfReleving;
	}
	public void setCtcTimeOfReleving(double ctcTimeOfReleving) {
		this.ctcTimeOfReleving = ctcTimeOfReleving;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
}
	
	