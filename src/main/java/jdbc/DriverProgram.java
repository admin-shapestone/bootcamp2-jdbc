package jdbc;

import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shapestone.jdbc.CompanyPojo;
import com.shapestone.jdbc.CompanyTable;
import com.shapestone.jdbc.EmployeePojo;
import com.shapestone.jdbc.EmployeeTable;

public class DriverProgram {

	public static void main(String[] args) throws SQLException, StreamReadException, DatabindException, IOException {
		
		final String URL = "jdbc:mysql://localhost:3306/employeedomain";
	    final String USER = "root";
		final String PASSWORD = "Gopikrishna@9542";
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<EmployeePojo> employeeList = (ArrayList<EmployeePojo>) mapper.readValue(new java.io.File("C:\\Users\\gopik\\git\\bootcamp2-jdbc\\src\\main\\java\\com\\shapestone\\jdbc\\employeejdc.json"),
				new TypeReference<List<EmployeePojo>>() {
				});
		List<CompanyPojo> companyList =(ArrayList<CompanyPojo>) mapper.readValue(new File("C:\\Users\\gopik\\git\\bootcamp2-jdbc\\src\\main\\java\\com\\shapestone\\jdbc\\companyjdc.json"),
				new TypeReference<List<CompanyPojo>>() {});

Connection connect = DriverManager.getConnection(URL,USER,PASSWORD);
System.out.println("sucessfully connected");
java.sql.Statement statement = connect.createStatement();
 
 statement.execute(EmployeeTable.createTable(employeeList));
 statement.execute(CompanyTable.createTable(companyList));
 System.out.println("table created sucessfully");
 //statement.execute(CompanyTable.prepareInsertQueries(companyList));
 
  ArrayList<String>prepareInsertQueries = EmployeeTable.prepareInsertQueries(employeeList);
  insertRecordsIntoemployeeList(prepareInsertQueries,statement);
  ArrayList<String> prepareInsertQueries1 = CompanyTable.prepareInsertQueries(companyList);
  insertdataIntoCompany(prepareInsertQueries1,statement);
  
  
	}


	private static void insertdataIntoCompany(ArrayList<String> prepareInsertQueries, java.sql.Statement statement)throws SQLException 
	{
		for (String query : prepareInsertQueries)
		{
			statement.execute(query);
		}
	}

	private static void insertRecordsIntoemployeeList(ArrayList<String> prepareInsertQueries,java.sql.Statement statement) throws SQLException
	{
		for(String query : prepareInsertQueries)
		{
			statement.execute(query);
		}
		
	}
	
	
}


