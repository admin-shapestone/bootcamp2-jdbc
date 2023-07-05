package shapestone.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import shapestone.jdbc.constants.JdbcQueries;
import shapestone.jdbc.constants.JdbcUtil;
import shapestone.jdbc.model.Student;

public class JdbcDriverProgram {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, SQLException {
		dropStudentTable();
		createTables();
		List<Student> studentList = readJsonFile();
		insertDataIntoStudentTable(studentList);
			
		getAllRecords();
		
		
		
		System.out.println("Done with execution. Exiting out...");

	}

	private static void dropStudentTable() throws SQLException {
		Connection connnection = JdbcUtil.getConnnection();
		Statement createStatement = connnection.createStatement();
		createStatement.execute("drop table student");
		
	}

	private static void getAllRecords() throws SQLException {
		Connection connnection = JdbcUtil.getConnnection();
		Statement createStatement = connnection.createStatement();
		ResultSet resultSet = createStatement.executeQuery("select age as studentAge, name from student order by name asc");
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
		System.out.printf("|%-30s|%-30s|%-30s||%-30s|%n", "ID", "AGE", "NAME" , "LAST NAME");
		
		while(resultSet.next()) {
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			System.out.printf("|%-30s|%-30s|%-30s||%-30s|%n", null, resultSet.getInt("studentAge")
					, resultSet.getString("name") , null);
			
		
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
		
	}

	private static void insertDataIntoStudentTable(List<Student> studentList) throws SQLException {
		System.out.print("Insertion start....");
		Connection connnection = JdbcUtil.getConnnection();
		
		String insertQueryForStudentTable = JdbcQueries.getInsertQueryForStudentTable();
		PreparedStatement preparedStatement  = connnection.prepareStatement(insertQueryForStudentTable);
		for (Student student : studentList) {
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, Integer.valueOf(student.getAge()));
			preparedStatement.setString(4, student.getLastName());
			preparedStatement.execute();
		}
		connnection.close();
		System.out.println("Insertion completed...");
		
	}

	private static List<Student> readJsonFile() throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Student[] students = mapper.readValue(new File("C:\\Users\\surya\\shapestone\\projects\\bootcamp2\\bootcamp2-jdbc\\src\\main\\java\\shapestone\\jdbc\\student.json"), Student[].class);

		return Arrays.asList(students);
	}

	private static void createTables() {
		Connection connnection = null;
		System.out.println("Creating requred tables");
		try {
			connnection = JdbcUtil.getConnnection();
			if (null != connnection) {
				Statement createStatement = connnection.createStatement();
				String createStudentTableQuery = JdbcQueries.getCreateStudentTableQuery();
				createStatement.execute(createStudentTableQuery);
				System.out.println("Done with table creation...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
