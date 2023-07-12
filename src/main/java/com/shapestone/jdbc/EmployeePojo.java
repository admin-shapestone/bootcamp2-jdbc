package com.shapestone.jdbc;

public class EmployeePojo {
   

	private int empId;
	private String name;
	private int age;
	private String gender;
	private String address;
	private long phoneNumber;
	@Override
	public String toString() {
		return "EmployeePojo [empId=" + empId + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address="
				+ address + ", phoneNumber=" + phoneNumber + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
