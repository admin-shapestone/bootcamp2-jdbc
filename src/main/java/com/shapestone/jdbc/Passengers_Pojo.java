package com.shapestone.jdbc;

public class Passengers_Pojo {
	private int passengerId;
	private String name;
	private int age;
	private String gender;
	private String address;
	public int getPassengerId() {
		return passengerId;
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
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	@Override
	public String toString() {
		return "Passengers_Pojo [passengerId=" + passengerId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + "]";
	}


}
