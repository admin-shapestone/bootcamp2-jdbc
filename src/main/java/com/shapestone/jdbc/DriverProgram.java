package com.shapestone.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DriverProgram {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, SQLException {

		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("press one for create table");
		 * System.out.println("press two for Insert data"); int one = sc.nextInt(); int
		 * two = sc.nextInt();
		 */

		final String DB_URL = "jdbc:mysql://localhost:3306/TravelDomain";
		final String USER = "root";
		final String PASSWord = "Rayapati@786";

		ObjectMapper mapper = new ObjectMapper();
		List<Bookings_Pojo> bookings = mapper.readValue(new File("bookingss.json"),
				new TypeReference<List<Bookings_Pojo>>() {
				});
		List<Passengers_Pojo> passengersList = mapper.readValue(new File("passengerss.json"),
				new TypeReference<List<Passengers_Pojo>>() {
				});

		Connection connect = DriverManager.getConnection(DB_URL, USER, PASSWord);

		System.out.println("sucessfully connected");

		Statement statement = connect.createStatement();

		statement.execute(Table.createTable1());
		System.out.println("table created sucessfully");

		statement.execute(PassengersTable.createTable(passengersList));
		System.out.println("table created sucessfully");

		ArrayList<String> prepareInsertQueries = Table.prepareInsertQueries(bookings);
		insertRecordsIntoBookings(prepareInsertQueries, statement);
		ArrayList<String> prepareInsertQueriess = PassengersTable.prepareInsertQueriess(passengersList);
		insertdataIntoPassengers(prepareInsertQueriess, statement);

	}

	private static void insertdataIntoPassengers(ArrayList<String> prepareInsertQueriess, Statement statement)
			throws SQLException {
		for (String query : prepareInsertQueriess) {
			statement.execute(query);
		}
	}

	private static void insertRecordsIntoBookings(ArrayList<String> prepareInsertQueries, Statement statement)
			throws SQLException {

		for (String query : prepareInsertQueries) {
			statement.execute(query);
		}

	}

}
