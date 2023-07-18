package com.shapestone.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connections {

	public static Connection getConnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/employeedomain";
		String user = "root";
		String password = "Gopikrishna@9542";

		Connection connect = DriverManager.getConnection(url, user, password);
		System.out.println("Successfully connected");
		return connect;
	}

	public static void createTable() throws SQLException {
		Connection connect = getConnection();
		Statement statement = connect.createStatement();
		statement.execute("DROP TABLE IF EXISTS employee");
		statement.execute("DROP TABLE IF EXISTS company");
		

		StringBuffer sb = new StringBuffer("CREATE TABLE employee (");
		sb.append("empId INT PRIMARY KEY,");
		sb.append("name VARCHAR(255) NOT NULL,");
		sb.append("age INT NOT NULL,");
		sb.append("gender VARCHAR(10) NOT NULL,");
		sb.append("address VARCHAR(255) NOT NULL,");
		sb.append("phoneNumber BIGINT NOT NULL)");
		String createEmployeeTableQuery = sb.toString();
		statement.execute(createEmployeeTableQuery);

		StringBuffer sbb = new StringBuffer("CREATE TABLE company (");
		sbb.append("empId INT,");
		sbb.append("companyId INT PRIMARY KEY,");
		sbb.append("emailId VARCHAR(255) NOT NULL,");
		sbb.append("dateOfReleving DATE NOT NULL,");
		sbb.append("amountReceived DECIMAL(10, 2) NOT NULL,");
		sbb.append("companyName VARCHAR(255) NOT NULL,");
		sbb.append("ctcTimeOfReleving DECIMAL(10, 2) NOT NULL,");
		sbb.append("dateOfJoining DATE NOT NULL,");
		sbb.append("FOREIGN KEY (empId) REFERENCES employee(empId))");
		String createCompanyTableQuery = sbb.toString();
		statement.execute(createCompanyTableQuery);

		System.out.println("Tables created successfully");
	}

	public static void insertData(List<EmployeePojo> employees, List<CompanyPojo> companies) throws SQLException {
		Connection connection = getConnection();

		// Insert data into the 'employee' table
		String insertEmployeeQuery = "INSERT INTO employee (empId, name, age, gender, address, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement employeeStatement = connection.prepareStatement(insertEmployeeQuery);
		for (EmployeePojo employee : employees) {
			employeeStatement.setInt(1, employee.getEmpId());
			employeeStatement.setString(2, employee.getName());
			employeeStatement.setInt(3, employee.getAge());
			employeeStatement.setString(4, employee.getGender());
			employeeStatement.setString(5, employee.getAddress());
			employeeStatement.setLong(6, employee.getPhoneNumber());
			employeeStatement.executeUpdate();
		}

		// Insert data into the 'company' table
		String insertCompanyQuery = "INSERT INTO company (empId, companyId, emailId, dateOfReleving, amountReceived, companyName, ctcTimeOfReleving, dateOfJoining) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement companyStatement = connection.prepareStatement(insertCompanyQuery);
		for (CompanyPojo company : companies) {
			companyStatement.setInt(1, company.getEmpId());
			companyStatement.setInt(2, company.getCompanyId());
			companyStatement.setString(3, company.getEmailId());
			companyStatement.setDate(4, company.getDateOfReleving());
			companyStatement.setDouble(5, company.getAmountReceived());
			companyStatement.setString(6, company.getCompanyName());
			companyStatement.setDouble(7, company.getCtcTimeOfReleving());
			companyStatement.setDate(8, company.getDateOfJoining());
			companyStatement.executeUpdate();
		}

		System.out.println("Data inserted successfully");
	}

	public static List<EmployeePojo> readData(int empId) throws SQLException {
		Connection connect = getConnection();
		String sql = "select * from employee WHERE empid =?";
		PreparedStatement statement = connect.prepareStatement(sql);
		statement.setInt(1, empId);
		ResultSet resultSet = statement.executeQuery();

		List<EmployeePojo> employee = new ArrayList<>();

		while (resultSet.next()) {
			EmployeePojo employee1 = new EmployeePojo();
			employee1.setEmpId(resultSet.getInt("empid"));
			employee1.setName(resultSet.getString("name"));
			employee1.setEmpId(resultSet.getInt("empid"));
			employee1.setAge(resultSet.getInt("age"));
			employee1.setGender(resultSet.getString("gender"));
			employee1.setAddress(resultSet.getString("address"));
			employee1.setPhoneNumber(resultSet.getLong("phoneNumber"));
			employee.add(employee1);
		}

		return employee;
	}

	public static void update(int age, int empid) throws SQLException {
	    Connection connect = getConnection();
	    String sql = "UPDATE employee SET age = ? WHERE empId = ?";
	    PreparedStatement statement = connect.prepareStatement(sql);
	    statement.setInt(1, age);
	    statement.setInt(2, empid);

	    int rowsAffected = statement.executeUpdate();
	    if (rowsAffected == 0) {
	        System.out.println("Invalid employee ID. Please enter a valid employee ID.");
	    } else {
	        System.out.println("Employee updated successfully.");
	    }
	}


	public static void deleteData(int empid) throws SQLException {
	    Connection connect = getConnection();
	    String sql = "DELETE FROM employee WHERE empid = ?";
	    PreparedStatement statement = connect.prepareStatement(sql);
	    statement.setInt(1, empid);

	    int rowsAffected = statement.executeUpdate();
	    if (rowsAffected == 0) {
	        System.out.println("Invalid employee ID. Please enter a valid employee ID.");
	    } else {
	        System.out.println("Employee deleted successfully.");
	    }
	}


	public static void employeeDetailsInAscendingOrder() throws SQLException {
		Connection connect = getConnection();
		String query = "SELECT name FROM employee ORDER BY name ASC";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println("Employee Names in Ascending Order:");
		System.out.println("+-----------------------+");
		System.out.println("|        Name           |");
		System.out.println("+-----------------------+");

		while (resultSet.next()) {
			String name = resultSet.getString("name");
			System.out.printf("| %-20s |\n", name);
		}

		System.out.println("+-----------------------+");

	}

	public static void currentWeek() throws SQLException {
		Connection connect = getConnection();
		String query = "select * from company where week(DateOfJoining) = week (curdate());";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.printf("| %-20s|%-20s|%-20s|%n", "empId", "companyId", "DateOfJoining");
		while (resultSet.next()) {
			System.out
					.println("+------------------------------------------------------------------------------------+");
			System.out.printf("| %-20s|%-20s|%-20s|%n", resultSet.getInt("empId"), resultSet.getInt("companyId"),
					resultSet.getString("DateOfJoining"));
		}
	}

	public static void totalCost(int empId) throws SQLException {
		Connection connect = getConnection();
		String query = "SELECT SUM(ctcTimeOfReleving) AS total_cost from company  WHERE empId = ?;";
		PreparedStatement statement = connect.prepareStatement(query);
		statement.setInt(1, empId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			double totalCost = resultSet.getDouble("total_cost");
			System.out.println("Total Cost for employee " + empId + ": $" + totalCost);
		}
		resultSet.close();
		statement.close();
		connect.close();
	}

}
