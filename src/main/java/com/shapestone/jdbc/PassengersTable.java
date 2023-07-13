package com.shapestone.jdbc;

import java.util.ArrayList;
import java.util.List;

public class PassengersTable {
	public static String createTable(List<Passengers_Pojo> passengersList) {
		StringBuilder sb = new StringBuilder("CREATE TABLE passengers (");
		sb.append("PassengerId INT PRIMARY KEY,");
		sb.append("Name VARCHAR(30) NOT NULL,");
		sb.append("Age INT NOT NULL,");
		sb.append("Gender VARCHAR(10) NOT NULL,");
		sb.append("Address VARCHAR(20) NOT NULL)");
		return sb.toString();
	}

	public static ArrayList<String> prepareInsertQueriess(List<Passengers_Pojo> passengersList) {
		ArrayList<String> queryList = new ArrayList<String>();
		for (int i = 0; i < passengersList.size(); i++) {
			StringBuilder sb = new StringBuilder("INSERT INTO passengers (");
			sb.append("passengerId, name, age, gender, address) ");
			sb.append("VALUES ('");
			sb.append(passengersList.get(i).getPassengerId() + "', ");
			sb.append("'" + passengersList.get(i).getName() + "', ");
			sb.append("'" + passengersList.get(i).getAge() + "', ");
			sb.append("'" + passengersList.get(i).getGender() + "', ");
			sb.append("'" + passengersList.get(i).getAddress() + "')");

			queryList.add(sb.toString());
		}
		return queryList;
	}
}
