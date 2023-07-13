package com.shapestone.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Statement;

public class DriverPro {
	public static void main(String[] args) throws SQLException, StreamReadException, DatabindException, IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("enter option 1 for Table Creation");
		System.out.println("Enter option 2 for Read ");
		System.out.println("Enter option 3 for Update ");
		System.out.println("Enter option 4 for Delete ");
		System.out.println("Enter option 5 for Print passengers names by assending order");
		System.out.println("Enter option 6 for Print total bookings on this week");
		System.out.println("Enter option 7 for Print total cost for passengers");

		int choice = sc.nextInt();
		ObjectMapper mapper = new ObjectMapper();

		List<Bookings_Pojo> bookingsList = mapper.readValue(new File("bookingss.json"),
				new TypeReference<List<Bookings_Pojo>>() {
				});
		List<Passengers_Pojo> passengersList1 = mapper.readValue(new File("passengers.json"),
				new TypeReference<List<Passengers_Pojo>>() {
				});

		if (choice == 1) {
			UserOptions.tableCreation(bookingsList, passengersList1);
		} else if (choice == 2) {
			UserOptions.readData();
		} else if (choice == 3) {
			System.out.println("Enter the age for Update");
			int age = sc.nextInt();
			System.out.println("Enter the PassengerId");
			int passengerid = sc.nextInt();
			UserOptions.update(age, passengerid);
		} else if (choice == 4) {
			System.out.println("Enter BookingId to Delete");
			int bookingId = sc.nextInt();
			UserOptions.deleteData(bookingId);
		} else if (choice == 5) {
			UserOptions.passengersNamesInAscending();
		} else if (choice == 6) {
			UserOptions.currentWeek();
		} else if (choice == 7) {
			System.out.println("Enter PassengerId");
			String PassengerId = sc.next();
			UserOptions.totalCost(PassengerId);
		}

	}
}
