
package shapestone.jdbc.mysql;

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

public class Connections {
	public static void table(List<PatientPojo> patientList, List<TreatmentPojo> treatmentList) throws SQLException {
		getConnection();
		createTable();
		insertData(patientList, treatmentList);

	}

	public static Connection getConnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/learning sql";
		String user = "root";
		String password = "18911A0294";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("Successfully connected");
		return con;
	}

	public static void createTable() throws SQLException {
		Connection connect = getConnection();

		Statement statement = connect.createStatement();

		String query = "DROP TABLE IF EXISTS patients";
		String query1 = "DROP TABLE IF EXISTS treatments";
		statement.execute(query1);
		statement.execute(query);

		// Create 'patients' table
		StringBuilder patientsTableQuery = new StringBuilder("CREATE TABLE patients (");
		patientsTableQuery.append("patientId INT PRIMARY KEY, ");
		patientsTableQuery.append("name VARCHAR(50) NOT NULL, ");
		patientsTableQuery.append("age INT NOT NULL, ");
		patientsTableQuery.append("gender VARCHAR(10) NOT NULL, ");
		patientsTableQuery.append("address VARCHAR(100) NOT NULL");
		patientsTableQuery.append(")");
		statement.execute(patientsTableQuery.toString());

		// Create 'treatments' table
		StringBuilder treatmentsTableQuery = new StringBuilder("CREATE TABLE treatments (");
		treatmentsTableQuery.append("treatmentId INT PRIMARY KEY, ");
		treatmentsTableQuery.append("patientId INT NOT NULL, ");
		treatmentsTableQuery.append("diagnosis VARCHAR(50) NOT NULL, ");
		treatmentsTableQuery.append("treatment VARCHAR(50) NOT NULL, ");
		treatmentsTableQuery.append("dateOfTreatment DATE NOT NULL, ");
		treatmentsTableQuery.append("cost DECIMAL(10, 2) NOT NULL, ");
		treatmentsTableQuery.append("FOREIGN KEY (patientId) REFERENCES patients(patientId)");
		treatmentsTableQuery.append(")");
		statement.execute(treatmentsTableQuery.toString());

		System.out.println("Both tables are created successfully.");

	}

	public static void insertData(List<PatientPojo> patientList, List<TreatmentPojo> treatmentList)
			throws SQLException {
		Connection connect = getConnection();
		String sql = "INSERT INTO patients (name, patientId, age, gender, address) VALUES (?, ?, ?, ?, ?)";
		String sqll = "INSERT INTO treatments(treatmentId,patientId,diagnosis,treatment,dateOfTreatment,cost) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connect.prepareStatement(sql);
		PreparedStatement statementt = connect.prepareStatement(sqll);
		for (PatientPojo patient : patientList) {
			statement.setString(1, patient.getName());
			statement.setInt(2, patient.getpatientId());
			statement.setInt(3, patient.getAge());
			statement.setString(4, patient.getGender());
			statement.setString(5, patient.getAddress());
			statement.executeUpdate();
		}
		for (TreatmentPojo treatment : treatmentList) {
			statementt.setInt(1, treatment.getTreatmentId());
			statementt.setInt(2, treatment.getPatientId());
			statementt.setString(3, treatment.getDiagnosis());
			statementt.setString(4, treatment.getTreatment());
			statementt.setString(5, treatment.getDateOfTreatment());
			statementt.setDouble(6, treatment.getCost());
			statementt.executeUpdate();
		}

		System.out.println("both tables data inserted successfully");
	}

	public static void readData() throws SQLException {

		Connection connect = getConnection();
		String sql = "SELECT * FROM patients";
		PreparedStatement statement = connect.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		System.out.printf("%-10s %-10s %-10s %-10s %-20s%n", "PatientId", "Name", "Age", "Gender", "Address");
		while (resultSet.next()) {
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.printf("%-10d %-10s %-10d %-10s %-20s%n", resultSet.getInt("PatientId"),
					resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("gender"),
					resultSet.getString("address"));
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	public static void update(String address, int patientid) throws SQLException {
		Connection connect = getConnection();
		String sql = "UPDATE patients SET address = ? WHERE patientid = ?";
		PreparedStatement statement = connect.prepareStatement(sql);
		statement.setString(1, address);
		statement.setInt(2, patientid);
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected > 0) {
			System.out.println("Patients updated successfully");
		} else {
			System.out.println("Invalid patientId: " + patientid);
		}
	}

	public static void deleteData(int treatmentId) throws SQLException {
		Connection connect = getConnection();
		String sql = "DELETE FROM treatments WHERE treatmentId=?";
		PreparedStatement statement = connect.prepareStatement(sql);

		statement.setInt(1, treatmentId);
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected > 0) {
			System.out.println("Entered treatmentid was deleted successfully");
		} else {
			System.out.println("Invalid treatmentId: " + treatmentId);
		}
	}

	public static void patientNamesInAscending() throws SQLException {

		Connection connect = getConnection();
		String query = "SELECT NAME FROM patients ORDER BY NAME ASC;";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString("NAME"));
		}

		System.out.println("sucessfully names sorted in asscending order");

	}

	public static void currentWeek() throws SQLException {
		Connection connection = getConnection();
		String query = "SELECT * FROM treatments WHERE WEEK(dateOfTreatment) = WEEK(CURDATE())";

		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		System.out.println(" successful");
		System.out.println("---------------------------------------------------------");
		System.out.printf("|%-10s|%-10s|%-10s|%n", "treatmentId", "patientId", "dateOfTreatment");
		while (resultSet.next()) {
			System.out.println("---------------------------------------------------------------");
			System.out.printf("|%-10s|%-10s|%-10s|%n", resultSet.getInt("treatmentId"), resultSet.getInt("patientId"),
					resultSet.getString("dateOfTreatment"));
		}
		System.out.println("-------------------------------------------------------------------");
	}

	public static void totalCost(int patientId) throws SQLException {
		Connection connect = getConnection();
		String query = "SELECT SUM(cost) AS total_cost FROM treatments WHERE patientId = ?;";
		PreparedStatement statement = connect.prepareStatement(query);
		statement.setInt(1, patientId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			double totalCost = resultSet.getDouble("total_cost");
			System.out.println("Total Cost for Patient " + patientId + ": $" + totalCost);
		}
	
		resultSet.close();
		statement.close();
		connect.close();
	}
	}


