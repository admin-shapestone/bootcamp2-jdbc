package com.shapestone.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserOptions {

	public static Connection getconnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/TravelDomain";
		String user = "root";
		String password = "Rayapati@786";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("Successfully connected");
		return con;
	}

	public static void createTable() throws SQLException {
		Connection connect = getconnection();

		Statement statement = connect.createStatement();

		String query = "DROP TABLE IF EXISTS passengers";
		String query1 = "DROP TABLE IF EXISTS bookingss";
		statement.execute(query1);
		statement.execute(query);

		// Create 'passengers' table
		StringBuilder passengersTableQuery = new StringBuilder("CREATE TABLE passengers (");
		passengersTableQuery.append("PassengerId INT PRIMARY KEY, ");
		passengersTableQuery.append("name VARCHAR(50) NOT NULL, ");
		passengersTableQuery.append("age INT NOT NULL, ");
		passengersTableQuery.append("gender VARCHAR(10) NOT NULL, ");
		passengersTableQuery.append("address VARCHAR(100) NOT NULL");
		passengersTableQuery.append(")");
		statement.execute(passengersTableQuery.toString());

		// Create 'bookings' table
		StringBuilder bookingsTableQuery = new StringBuilder("CREATE TABLE bookingss(");
		bookingsTableQuery.append("BookingId INT PRIMARY KEY, ");
		bookingsTableQuery.append("PassengerId INT NOT NULL, ");
		bookingsTableQuery.append("OriginFrom VARCHAR(40) NOT NULL, ");
		bookingsTableQuery.append("DestinationTo VARCHAR(40) NOT NULL, ");
		bookingsTableQuery.append("Distance BIGINT NOT NULL, ");
		bookingsTableQuery.append("ModeOfTransport VARCHAR(10) NOT NULL, ");
		bookingsTableQuery.append("PricePerKm FLOAT NOT NULL, ");
		bookingsTableQuery.append("dateOfJourney DATE NOT NULL, ");
		bookingsTableQuery.append("FOREIGN KEY (PassengerId) REFERENCES passengers(PassengerId)");
		bookingsTableQuery.append(")");
		statement.execute(bookingsTableQuery.toString());

		System.out.println("Both tables are created successfully.");

	}

	public static void insertData(List<Passengers_Pojo> passengersList, List<Bookings_Pojo> bookingsList)
			throws SQLException {
		Connection connect = getconnection();
		String sql = "INSERT INTO passengers (PassengerId, name, age, gender, address) VALUES (?, ?, ?, ?, ?)";
		String sqll = "INSERT INTO bookingss (BookingId, PassengerId, OriginFrom, DestinationTo, Distance, ModeOfTransport, PricePerKm, dateOfJourney) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connect.prepareStatement(sql);
		PreparedStatement statementt = connect.prepareStatement(sqll);

		for (Passengers_Pojo passengers : passengersList) {
			statement.setInt(1, passengers.getPassengerId());
			statement.setString(2, passengers.getName());
			statement.setInt(3, passengers.getAge());
			statement.setString(4, passengers.getGender());
			statement.setString(5, passengers.getAddress());
			statement.executeUpdate();
		}

		for (Bookings_Pojo bookings : bookingsList) {
			statementt.setInt(1, bookings.getBookingId());
			statementt.setInt(2, bookings.getPassengerId());
			statementt.setString(3, bookings.getOriginFrom());
			statementt.setString(4, bookings.getDestinationTo());
			statementt.setLong(5, bookings.getDistance());
			statementt.setString(6, bookings.getModeOfTransport());
			statementt.setFloat(7, bookings.getPricePerKm());
			statementt.setString(8, bookings.getDateOfJourney());

			statementt.executeUpdate();
		}

		System.out.println("Both tables data Inserted Successfully");
	}

	public static void readData() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Passenger ID: ");
		int passengerId = sc.nextInt();
		Connection connect = getconnection();
		String sql = "SELECT * FROM passengers WHERE PassengerId = ?";
		PreparedStatement statement = connect.prepareStatement(sql);
		statement.setInt(1, passengerId);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "PassengerId", "Name", "Age", "Gender", "Address");
			System.out.println("---------------------------------------------------------------");
			System.out.printf("%-10d %-20s %-10d %-10s %-20s%n", resultSet.getInt("PassengerId"),
					resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("gender"),
					resultSet.getString("address"));
			System.out.println("---------------------------------------------------------------");
		} else {
			System.out.println("Invalid PassengerID.No matching passenger found.: " + passengerId);
		}
	}

	public static void update(int age, int passengerId) throws SQLException {
		Connection connect = getconnection();
		String sql = "UPDATE passengers SET age = ? WHERE PassengerId = ?";
		PreparedStatement statement = connect.prepareStatement(sql);
		statement.setInt(1, age);
		statement.setInt(2, passengerId);
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected > 0) {
			System.out.println("Passenger updated successfully");
		} else {
			System.out.println("Invalid ID. No matching passenger found.");
		}
	}

	public static void deleteData(int bookingId) throws SQLException {
		Connection connect = getconnection();
		String sql = "DELETE FROM bookingss WHERE bookingId=?";
		PreparedStatement statement = connect.prepareStatement(sql);

		statement.setInt(1, bookingId);
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected > 0) {
			System.out.println("Entered ID was deleted successfully");
		} else {
			System.out.println("Invalid ID. No matching records found.");
		}
	}

	public static void passengersNamesInAscending() throws SQLException {

		Connection connect = getconnection();
		String query = "SELECT NAME FROM passengers ORDER BY NAME ASC;";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString("NAME"));
		}

		System.out.println("sucessfully names sorted in asscending order");

	}

	public static void currentWeek() throws SQLException {
		Connection connection = getconnection();
		String query = "SELECT * FROM bookingss WHERE WEEK(dateOfJourney) = WEEK(CURDATE())";

		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		System.out.println(" successful");
		System.out.println("--------------------------------------");
		System.out.printf("|%-10s|%-10s|%-10s|%n", "PassengerId", "BookingId", "DateOfJourney");
		while (resultSet.next()) {
			System.out.println("-------------------------------------");
			System.out.printf("|%-10s|%-10s|%-10s|%n", resultSet.getInt("PassengerId"), resultSet.getInt("BookingId"),
					resultSet.getString("dateOfJourney"));
		}
		System.out.println("-------------------------------------");
	}

	public static void totalCost(String PassengerId) throws SQLException {
		Connection connect = getconnection();
		String query = "SELECT SUM(distance * PricePerKm) AS total_cost FROM bookingss  WHERE passengerid = ?;";
		PreparedStatement statement = connect.prepareStatement(query);
		statement.setString(1, PassengerId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			double totalCost = resultSet.getDouble("total_cost");
			if (totalCost > 0) {
				System.out.println("Total Cost for passengers " + PassengerId + ": $" + totalCost);
			} else {
				System.out.println("Invalid PassengerId.No matching passenger found.: " + PassengerId);
			}
		}

	}

	public static <insertData> void tableCreation(List<Bookings_Pojo> bookingsList,
			List<Passengers_Pojo> passengersList) throws SQLException {
		getconnection();
		createTable();
		insertData(passengersList, bookingsList);
	}

}
