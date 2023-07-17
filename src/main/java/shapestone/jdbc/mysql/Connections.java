
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

	public static Connection getconnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/learning sql";
		String user = "root";
		String password = "18911A0294";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("Successfully connected");
		return con;
	}

	public static void createTable() throws SQLException {
		Connection connect = getconnection();

		Statement statement = connect.createStatement();
		statement.execute("DROP TABLE IF EXISTS patientquerry");

		StringBuilder sb = new StringBuilder("CREATE TABLE patientquerry (");
		sb.append("NAME VARCHAR(10) NOT NULL, ");
		sb.append("ID INT PRIMARY KEY, ");
		sb.append("AGE VARCHAR(10) NOT NULL, ");
		sb.append("GENDER VARCHAR(10) NOT NULL, ");
		sb.append("ADDRESS VARCHAR(20) NOT NULL,");
		sb.append("DIAGNOSIS VARCHAR(20) NOT NULL,");
		sb.append("TREATMENT VARCHAR(20) NOT NULL,");
		sb.append("DATEOFTREATMENT VARCHAR(20) NOT NULL,");
		sb.append("COST INT(20) NOT NULL)");

		statement.execute(sb.toString());

		System.out.println("Table 'patientquerry' created successfully.");
	}

	public static void insertData(List<PatientPojo> patientList) throws SQLException {
		Connection connect = getconnection();
		String sql = "INSERT INTO patientquerry (name, id, age, gender, address,diagnosis,treatment,dateoftreatment,cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connect.prepareStatement(sql);

		for (PatientPojo patient : patientList) {
			statement.setString(1, patient.getName());
			statement.setInt(2, patient.getId());
			statement.setInt(3, patient.getAge());
			statement.setString(4, patient.getGender());
			statement.setString(5, patient.getAddress());
			statement.setString(6, patient.getDiagnosis());
			statement.setString(7, patient.getTreatment());
			statement.setString(8, patient.getDateOfTreatment());
			statement.setInt(9, patient.getCost());
			statement.executeUpdate();
		}

		System.out.println("Patients created successfully");
	}

	public static void readData() throws SQLException {

		Connection connect = getconnection();
		String sql = "SELECT * FROM patientquerry";
		PreparedStatement statement = connect.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		System.out.printf("%-10s %-10s %-10s %-10s %-20s %-20s %-20s %-20s %-10s%n", "ID", "Name", "Age", "Gender",
				"Address", "Diagnosis", "Treatment", "Date of Treatment", "Cost");
		while (resultSet.next()) {
    System.out.println("------------------------------------------------------------------------------------------------"
    		+ "-------------------------------------------------------");
 			System.out.printf("%-10d %-10s %-10d %-10s %-20s %-20s %-20s %-20s %-10d%n", resultSet.getInt("id"),
					resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("gender"),
					resultSet.getString("address"), resultSet.getString("diagnosis"), resultSet.getString("treatment"),
					resultSet.getString("dateOfTreatment"), resultSet.getInt("cost"));
		}
  System.out.println("-------------------------------------------------------------------------------------------"
  		+ "-------------------------------------------------------------");
	}

	public static void update(int age, int id) throws SQLException {
		Connection connect = getconnection();
		String sql = "UPDATE patientquerry SET age = ? WHERE id = ?";
		PreparedStatement statement = connect.prepareStatement(sql);
		statement.setInt(1, age);
		statement.setInt(2, id);
		statement.execute();
		System.out.println("Patients updated successfully");
	}

	public static void deleteData(int id) throws SQLException {
		Connection connect = getconnection();
		String sql = "DELETE FROM patientquerry WHERE id=?";
		PreparedStatement statement = connect.prepareStatement(sql);

		statement.setInt(1, id);
		statement.execute();
		System.out.println("entered id was deleted successfully");
	}

	public static void patientNamesInAscending() throws SQLException {

		Connection connect = getconnection();
		String query = "SELECT NAME FROM patientquerry ORDER BY NAME ASC;";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString("NAME"));
		}

		System.out.println("sucessfully names sorted in asscending order");

	}

	public static void currentWeek() throws SQLException {
		Connection connection = getconnection();
		String query = "SELECT * FROM patientquerry WHERE WEEK(dateOfTreatment) = WEEK(CURDATE())";

		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		System.out.println(" successful");

		while (resultSet.next()) {
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------");
			System.out.printf("|%-30s|%-30s|%-30s|%-30s|%n", resultSet.getString("dateOfTreatment"),
					resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("name"));
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------");
	}

	public static void totalCost(String name) throws SQLException {
    		Connection connect=getconnection();
    		 String query = "SELECT SUM(cost) AS total_cost FROM patientquerry WHERE name = ?;";
             PreparedStatement statement = connect.prepareStatement(query);
             statement.setString(1, name);
             ResultSet resultSet = statement.executeQuery();
             if (resultSet.next()) {
                 int totalCost = resultSet.getInt("total_cost");
                 System.out.println("Total Cost for Patient " + name + "$" + totalCost);
             }
             resultSet.close();
             statement.close();
             connect.close();

    		
    		
	}

}




